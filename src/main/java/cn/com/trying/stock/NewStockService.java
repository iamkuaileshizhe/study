package cn.com.trying.stock;

import cn.com.trying.utils.MathUtil;
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
@RequestMapping("/stock")
public class NewStockService {
    public static void main(String[] args) {
        NewStockService newStockService = new NewStockService();
        newStockService.cacluNewStock(14.39,500,20);
    }

    @RequestMapping("home")
    String home() {
        String str = cacluNewStock(5.3,1000,20);
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
            String s = "第"+i+"板后的价格："+price+"------盈利："+profit;
            str += s +"<br/>";
            System.out.println(s);
        }
        return str;
    }
}
