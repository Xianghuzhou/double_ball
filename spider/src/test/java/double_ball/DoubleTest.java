package double_ball;

import double_ball.core.entiy.DoubleBall;
import double_ball.mail.MailSenderService;
import double_ball.utils.Analyzer.DoubleBallDomAnalyzer;
import double_ball.utils.Analyzer.impl.DoubleBallDomAnalyzerImpl;
import double_ball.utils.BoubleBallSpider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 类说明
 * @date 2017/9/8 上午10:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DoubleTest {

    private String url;

    @Autowired
    private MailSenderService mailSenderService;
    @Resource
    private DoubleBallDomAnalyzer doubleBallDomAnalyzer;

    @Before
    public void setUp(){
        this.url = "http://kaijiang.500.com/shtml/ssq/03001.shtml";
    }


    @Test
    public void test(){
//        try {
//            BoubleBallSpider.forEntityList(url, doubleBallDomAnalyzer, DoubleBall.class);
//        }catch (Exception e){
//
//        }

        mailSenderService.sendSimpleMail("958653609@qq.com","测试","1234");

    }
}
