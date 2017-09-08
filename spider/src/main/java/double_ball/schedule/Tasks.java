package double_ball.schedule;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 类说明
 * @date 2017/9/8 下午2:21
 */

import com.alibaba.fastjson.JSONObject;
import double_ball.core.entiy.DoubleBall;
import double_ball.core.service.DoubleBallService;
import double_ball.mail.MailSenderService;
import double_ball.plugins.JedisKit;
import double_ball.utils.Analyzer.DoubleBallDomAnalyzer;
import double_ball.utils.BoubleBallSpider;
import double_ball.utils.forecast.BallForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.xml.bind.PrintConversionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 定时任务
 *
 * @author haidi.chen
 */
@Component
@Configurable
@EnableScheduling
public class Tasks {

    @Autowired
    private JedisKit jedisKit;

    @Autowired
    private DoubleBallService doubleBallService;

    @Autowired
    private BallForecast ballForecast;

    @Autowired
    private MailSenderService mailSenderService;

    @Value("${doubleball.preurl}")
    private String url;

    /**
     * 更新新一期的双色球信息
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateDoubleBall() throws Exception {

        if(jedisKit.exists("periods_now")){
            Integer needPeriods = Integer.parseInt(jedisKit.get("periods_now"));

            Integer year = needPeriods/1000;

            Integer yearPeriod = needPeriods - year*1000;

            doubleBallService.insert(url,year*1000+yearPeriod+1);

            doubleBallService.insert(url,(year+1)*1000+yearPeriod);
        }
    }


    /**
     * 发送最可能的号码
     */
    @Scheduled(cron = "0 5 0 * * ?")
    public void forecast() throws Exception {

        if(!jedisKit.exists("ball")){
            Set<String> keys = jedisKit.hkeys("double_ball");

            List<DoubleBall> doubleBallList = new ArrayList<>();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()){
                String str = jedisKit.hget("double_ball",iterator.next());
                doubleBallList.add(JSONObject.parseObject(str,DoubleBall.class));
            }

            List<String> balls = ballForecast.luck(doubleBallList);
            String ball = String.join(",",balls);

            jedisKit.set("ball",ball);
            jedisKit.expire("ball",45*60*24*2);
            mailSenderService.sendSimpleMail("958653609@qq.com","双色球",ball);
        }
    }
}
