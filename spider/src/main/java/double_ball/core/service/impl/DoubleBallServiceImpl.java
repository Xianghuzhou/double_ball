package double_ball.core.service.impl;

import double_ball.core.entiy.DoubleBall;
import double_ball.core.repo.DoubleRepo;
import double_ball.core.service.DoubleBallService;
import double_ball.plugins.JedisKit;
import double_ball.utils.Analyzer.DoubleBallDomAnalyzer;
import double_ball.utils.BoubleBallSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 实现类
 * @date 2017/9/8 下午2:34
 */
@Service
public class DoubleBallServiceImpl implements DoubleBallService{

    @Autowired
    private DoubleRepo doubleRepo;

    @Autowired
    private JedisKit jedisKit;

    @Autowired
    private DoubleBallDomAnalyzer doubleBallDomAnalyzer;



    @Override
    @Transactional
    public void insert(String url,Integer periods) throws Exception{

        if(jedisKit.exists("double_ball",periods.toString())){//存在结束
            return;
        }

        if(periods<10000){
            url = url +"0"+periods+".shtml";
        }else {
            url = url+periods+".shtml";
        }

        //重试3次
        Integer i = 0;

        while (i<3){
            List<DoubleBall> doubleBalls = BoubleBallSpider.forEntityList(url,doubleBallDomAnalyzer,DoubleBall.class);
            if(doubleBalls!=null&&!doubleBalls.isEmpty()){
                DoubleBall doubleBall = doubleBalls.get(0);
                jedisKit.hset("double_ball",doubleBall.getPeriods().toString(), com.alibaba.fastjson.JSONObject.toJSONString(doubleBall));


                DoubleBall oldBall = doubleRepo.selectByPeriods(doubleBall.getPeriods());
                if(oldBall!=null){//update
                    doubleBall.setId(oldBall.getId());
                }
                doubleRepo.save(doubleBall);

                //更新最大的
                if(jedisKit.exists("periods_now")){
                    Integer perviodsNow = Integer.parseInt(jedisKit.get("periods_now"));
                    if(perviodsNow<periods){
                        jedisKit.set("periods_now",periods.toString());
                    }
                }else {
                    jedisKit.set("periods_now",periods.toString());
                }

                break;
            }

            i++;
        }

    }
}
