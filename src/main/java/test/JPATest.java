package test;

import cn.com.trying.Application;
import cn.com.trying.stock.finacial.bean.Financial;
import cn.com.trying.stock.finacial.dao.FinancialRepository;
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
public class JPATest {
    Logger logger = LoggerFactory.getLogger(JPATest.class);
    @Autowired
    private FinancialRepository financialRepository;

    @Test
    public void testInsert() {
        String time = DateUtil.getDate();
        int insertNum = 2;
        insert("601038",-2395,10.17,time,insertNum);
        insert("600127",-1844,11.49,time,insertNum);

    }

    private void insert(String code,double amount,double price,String time,int num){
        Financial financial = new Financial();
        financial.setCode(code);
        financial.setAmount(amount);
        financial.setPrice(price);
        financial.setTime(time);
        financial.setNum(num);
        Financial financial1 = financialRepository.save(financial);
        assert(financial1 != null);
        System.out.println("插入成功！");
    }

    @Test
    public void testSelect(){

        List<Financial> list = financialRepository.findAllByCode("601038");
        list.stream().forEach(financial -> {
            logger.error("{}---{}---{}---{}",financial.getCode(),financial.getTime(),financial.getNum(),financial.getPrice());
        });
    }
}
