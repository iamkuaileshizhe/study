package cn.com.trying.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/**
* @Title: StockCrawlerService
* @Description: 爬虫示例
* @author huxx
* @date 2020/5/28 下午1:43
* @update
*/
@Service
public class StockCrawlerService {
    Logger logger = LoggerFactory.getLogger(StockCrawlerService.class);
    public void crawlStock(String code){
        try{
            Document doc = Jsoup.connect("http://www.baidu.com/s?ie=UTF-8&wd=jsoup").get();
            String title = doc.title();
            logger.error(title);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
