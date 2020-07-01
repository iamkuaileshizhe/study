package test;

import cn.com.trying.Application;
import cn.com.trying.certificate.CertUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CertTest {
    Logger logger = LoggerFactory.getLogger(CertTest.class);

    @Test
    public void test(){
        CertUtil.testCertificate();
    }
}
