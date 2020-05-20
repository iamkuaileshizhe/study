package test;

import cn.com.trying.Application;
import cn.com.trying.test.jpa.bean.Financial;
import cn.com.trying.test.jpa.dao.FinancialRepository;
import cn.com.trying.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class JPATest {
    @Autowired
    private FinancialRepository financialRepository;

    @Test
    public void testInsert() {
        String time = DateUtil.getDateTime();
        insert("601038",-2395,10.17,time);
        insert("600127",-1844,11.49,time);

    }

    private void insert(String code,double num,double price,String time){
        Financial financial = new Financial();
        financial.setCode(code);
        financial.setNum(num);
        financial.setPrice(price);
        financial.setTime(time);
        Financial financial1 = financialRepository.save(financial);
        assert(financial1 != null);
        System.out.println("插入成功！");
    }
}
