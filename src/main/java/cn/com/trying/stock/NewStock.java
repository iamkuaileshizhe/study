package cn.com.trying.stock;

import cn.com.trying.io.ExportDataFromExcel;
import cn.com.trying.utils.MathUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Title: NewStock
* @Description:
* @author huxx
* @date 2019/9/19 下午3:20
* @update
*/
@RestController
@EnableAutoConfiguration
public class NewStock {
    public static void main(String[] args) {
//       String str = cacluNewStock(14.39,500,20);
//       System.out.println(str);
        SpringApplication.run(NewStock.class, args);
        ExportDataFromExcel.generateSql();
    }

    @RequestMapping("/")
    String home() {
        String str = cacluNewStock(14.39,500,20);
        return str;
    }
    /**
    * @Title:
    * @Description:
    * @param
    * @return
    * @author huxx
    * @date 2019/9/19 下午3:20
    * @update
    */
    public  String cacluNewStock(double startPrice,int stockCount,int num){
        String str = "";
        double price = startPrice;
        for(int i = 1; i<= num; i++){
            double inc = 0.44;
            if(i > 1){
                inc = 0.1;
            }
            price = Double.valueOf(MathUtil.mutiply(price + "", (1 + inc) + "", 2));
            String profit = MathUtil.mutiply((price-startPrice)+"",stockCount+"",2);
            str += "第"+i+"板后的价格："+price+"------盈利："+profit+"<br/>";
        }
        return str;
    }
}
