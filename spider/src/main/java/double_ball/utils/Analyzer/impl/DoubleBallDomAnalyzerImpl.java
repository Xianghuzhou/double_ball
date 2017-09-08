package double_ball.utils.Analyzer.impl;

import double_ball.core.entiy.DoubleBall;
import double_ball.utils.Analyzer.DoubleBallDomAnalyzer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xianghuzhou
 * @version 1.0
 * @Description : 解析器实现
 * @date 2017/9/8 上午10:26
 */
@Component
public class DoubleBallDomAnalyzerImpl implements DoubleBallDomAnalyzer {

    @Override
    /**
     * 根据html文档对象获取List<Map>
     *
     * @param document html文档对象
     * @return 结果
     */
    public List<Map<String, Object>> forListMap(Document document) {
        List<Map<String, Object>> results = new ArrayList<>();
        if (ObjectUtils.isEmpty(document))
            return results;

        List<String> ballparams = new ArrayList<>();
        ballparams.add("firstRedBall");
        ballparams.add("secondRedBall");
        ballparams.add("thirdRedBall");
        ballparams.add("fouthRedBall");
        ballparams.add("fifthRedBall");
        ballparams.add("sixthRedBall");
        ballparams.add("firstBlueBall");
        Map<String, Object> result = new HashMap<>();
        Elements elements = document.body().getElementsByClass("ball_box01").get(0).child(0).children();
        for (int i = 0; i < elements.size(); i++) {
            result.put(ballparams.get(i), Integer.parseInt(elements.get(i).getElementsByTag("li").text()));
        }

        Element element = document.body().getElementsByClass("cfont2").get(0).child(0);
        result.put("periods",Integer.parseInt(element.text()));

        Element element1 = document.body().getElementsByClass("span_right").get(0);
        String datestr = element1.text().substring(5,16);
        System.out.println(element1.text().substring(5,16));
        String year = datestr.substring(0,datestr.indexOf("年"));

        String month = datestr.substring(datestr.indexOf("年")+1,datestr.indexOf("月"));
        if(month.length()==1){
            month =  "0"+month;
        }
        String day = datestr.substring(datestr.indexOf("月")+1,datestr.indexOf("日"));

        datestr = year+"-"+month+"-"+day;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(datestr);
            result.put("date",date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        results.add(result);

        return results;
    }
}
