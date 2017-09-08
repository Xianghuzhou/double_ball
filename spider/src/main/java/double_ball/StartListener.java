package double_ball;

import double_ball.core.service.DoubleBallService;
import double_ball.plugins.JedisKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 初始化
 * @date 2017/9/8 下午3:58
 */
@Component
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DoubleBallService doubleBallService;

    @Autowired
    private JedisKit jedisKit;

    @Value("${doubleball.preurl}")
    private String url;

    private final Logger logger = LoggerFactory.getLogger(StartListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
//            logger.info("onApplicationEvent ");
//
//            for (int i = 3; i <20 ; i++) {
//                for (int j = 1; j < 184; j++) {
//                    try {
//                    doubleBallService.insert(url,i*1000+j);
//                }catch (Exception e){
//
//                }}
//            }
//        }
    }
}
