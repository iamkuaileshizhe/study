package cn.com.trying.stock;

import cn.com.trying.utils.MathUtil;

public class NewStock {
    public static void main(String[] args) {

        double basePrice = 14.39;
        int baseCount = 500;
        int count = 15;

        double price = basePrice;
        for(int i = 1; i<= count; i++){
            double inc = 0.44;
            if(i > 1){
                inc = 0.1;
            }
            price = Double.valueOf(MathUtil.mutiply(price + "", (1 + inc) + "", 2));
            String profit = MathUtil.mutiply((price-basePrice)+"",baseCount+"",2);
            System.out.println("第"+i+"板后的价格："+price+"------盈利："+profit);
        }

    }
}
