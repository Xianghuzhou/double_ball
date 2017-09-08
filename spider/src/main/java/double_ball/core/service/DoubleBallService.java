package double_ball.core.service;

import org.springframework.stereotype.Service;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : service实现
 * @date 2017/9/8 下午2:34
 */
@Service
public interface DoubleBallService{

    void insert(String url,Integer persiods) throws Exception;
}
