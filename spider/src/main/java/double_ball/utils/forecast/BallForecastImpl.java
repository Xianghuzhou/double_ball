package double_ball.utils.forecast;

import double_ball.core.entiy.DoubleBall;
import double_ball.plugins.JedisKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 预测实现类
 * @date 2017/9/8 下午9:40
 */
@Component
public class BallForecastImpl implements BallForecast{

    @Autowired
    private JedisKit jedisKit;

    @Override
    public List<String> luck(List<DoubleBall> doubleBalls) {

        List<String> strings = new ArrayList<>();

        jedisKit.del("red_num");

        jedisKit.del("blue_num");

        Set<String> total = new TreeSet<>();

        doubleBalls.stream().forEach(ball->{
            Integer firstRed=ball.getFirstRedBall();
            Integer secondRed=ball.getSecondRedBall();
            Integer thirdRed=ball.getThirdRedBall();
            Integer fourthRed=ball.getFouthRedBall();
            Integer fifthRed=ball.getFifthRedBall();
            Integer sixthRed=ball.getSixthRedBall();
            jedisKit.zSetAdd("red_num",firstRed.toString());
            jedisKit.zSetAdd("red_num",secondRed.toString());
            jedisKit.zSetAdd("red_num",thirdRed.toString());
            jedisKit.zSetAdd("red_num",fourthRed.toString());
            jedisKit.zSetAdd("red_num",fifthRed.toString());
            jedisKit.zSetAdd("red_num",sixthRed.toString());

            Integer firstBlue=ball.getFirstBlueBall();
            jedisKit.zSetAdd("blue_num",firstBlue.toString());
        });

        Set<String> firstBlue = jedisKit.zSetGet("blue_num",0,0);

        Set<String> firstRed = jedisKit.zSetGet("red_num",0,0);
        Set<String> secondRed = jedisKit.zSetGet("red_num",5,5);
        Set<String> thirdRed = jedisKit.zSetGet("red_num",10,10);
        Set<String> fourthRed = jedisKit.zSetGet("red_num",15,15);
        Set<String> fifthRed = jedisKit.zSetGet("red_num",20,20);
        Set<String> sixRed = jedisKit.zSetGet("red_num",25,25);

        total.addAll(firstRed);
        total.addAll(secondRed);
        total.addAll(thirdRed);
        total.addAll(fourthRed);
        total.addAll(fifthRed);
        total.addAll(sixRed);

        Iterator<String> iterator = total.iterator();
        while (iterator.hasNext()){
            strings.add(iterator.next());
        }
        strings.add(firstBlue.iterator().next());

        return strings;
    }
}
