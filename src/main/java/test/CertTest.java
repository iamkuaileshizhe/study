package test;

import cn.com.trying.Application;
import cn.com.trying.certificate.CertUtil;
import cn.com.trying.crawler.service.StockCrawlerService;
import cn.com.trying.stock.financial.bean.Financial;
import cn.com.trying.stock.financial.bean.StockBean;
import cn.com.trying.stock.financial.dao.FinancialRepository;
import cn.com.trying.stock.financial.dao.StockInfoRepository;
import cn.com.trying.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CertTest {
    Logger logger = LoggerFactory.getLogger(CertTest.class);

    @Test
    public void test(){
        CertUtil.testCertificate();
    }
}
