package double_ball.utils;

import double_ball.utils.Analyzer.DoubleBallDomAnalyzer;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 抓取双色球信息
 * @date 2017/9/8 上午10:22
 */
public class BoubleBallSpider {
    private static final Logger log = LoggerFactory.getLogger(BoubleBallSpider.class);

    public static <T> List<T> forEntityList(String url, DoubleBallDomAnalyzer docAnalyzer, Class<T> type) throws Exception {

        log.info("开始抓取文章："+url);

        List<T> results = new ArrayList<>();
        List<Map<String, Object>> maps = docAnalyzer.forListMap(Jsoup.connect(url).timeout(50000).get());

        for (int i = 0; i < maps.size(); i++) {
            try {
                T entiy=TinyUtil.mapToBean(maps.get(i), type);
                results.add(entiy);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        return results;
    }
}
