package double_ball.core.repo;

import double_ball.core.entiy.DoubleBall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 数据接口
 * @date 2017/9/8 下午2:32
 */
@Repository
public interface DoubleRepo extends JpaRepository<DoubleBall,Long>{

    @Query("SELECT ball FROM DoubleBall ball where periods = ?1")
    DoubleBall selectByPeriods(Integer periods);
}
