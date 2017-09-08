package double_ball.utils.Analyzer;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description :  解析器接口
 * @date 2017/9/8 上午10:24
 */
public interface DoubleBallDomAnalyzer {
    /**
     * 根据html文档对象获取List<Map>
     *
     * @param document html文档对象
     * @return 结果
     */
    List<Map<String, Object>> forListMap(Document document);
}
