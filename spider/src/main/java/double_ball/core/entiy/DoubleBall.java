package double_ball.core.entiy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 双色球实体
 * @date 2017/9/8 上午10:09
 */

@Entity
@Table(name = "double_ball")
public class DoubleBall extends BaseEnity{

    private Integer firstRedBall;

    private Integer secondRedBall;

    private Integer thirdRedBall;

    private Integer fouthRedBall;

    private Integer fifthRedBall;

    private Integer sixthRedBall;

    private Integer firstBlueBall;

    private Integer periods;

    private Date date;

    public Integer getFirstRedBall() {
        return firstRedBall;
    }

    public void setFirstRedBall(Integer firstRedBall) {
        this.firstRedBall = firstRedBall;
    }

    public Integer getSecondRedBall() {
        return secondRedBall;
    }

    public void setSecondRedBall(Integer secondRedBall) {
        this.secondRedBall = secondRedBall;
    }

    public Integer getThirdRedBall() {
        return thirdRedBall;
    }

    public void setThirdRedBall(Integer thirdRedBall) {
        this.thirdRedBall = thirdRedBall;
    }

    public Integer getFouthRedBall() {
        return fouthRedBall;
    }

    public void setFouthRedBall(Integer fouthRedBall) {
        this.fouthRedBall = fouthRedBall;
    }

    public Integer getFifthRedBall() {
        return fifthRedBall;
    }

    public void setFifthRedBall(Integer fifthRedBall) {
        this.fifthRedBall = fifthRedBall;
    }

    public Integer getSixthRedBall() {
        return sixthRedBall;
    }

    public void setSixthRedBall(Integer sixthRedBall) {
        this.sixthRedBall = sixthRedBall;
    }

    public Integer getFirstBlueBall() {
        return firstBlueBall;
    }

    public void setFirstBlueBall(Integer firstBlueBall) {
        this.firstBlueBall = firstBlueBall;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
