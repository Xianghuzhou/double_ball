package double_ball.utils.forecast;

import double_ball.core.entiy.DoubleBall;

import java.util.List;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 预测
 * @date 2017/9/8 下午9:33
 */
public interface BallForecast {

    List<String> luck(List<DoubleBall> doubleBalls);
}
