package cn.com.trying.stock;

import cn.com.trying.utils.MathUtil;
/**
* @Title: NewStock
* @Description: 
* @author huxx
* @date 2019/9/19 下午3:20
* @update  
*/
public class NewStock {
    public static void main(String[] args) {

        double basePrice = 14.39;
        int baseCount = 500;
        int count = 50;

        cacluNewStock(14.39,500,20);


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
    public static void cacluNewStock(double startPrice,int stockCount,int num){
        double price = startPrice;
        for(int i = 1; i<= num; i++){
            double inc = 0.44;
            if(i > 1){
                inc = 0.1;
            }
            price = Double.valueOf(MathUtil.mutiply(price + "", (1 + inc) + "", 2));
            String profit = MathUtil.mutiply((price-startPrice)+"",stockCount+"",2);
            System.out.println("第"+i+"板后的价格："+price+"------盈利："+profit);
        }
    }
}
