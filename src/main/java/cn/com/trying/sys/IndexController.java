package cn.com.trying.sys;

import cn.com.trying.stock.NewStockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    public static void main(String[] args) {
        SpringApplication.run(IndexController.class, args);
    }

    @RequestMapping("/")
    String home() {
        return "/index.html";
    }

    @RequestMapping("/newStock")
    String newSotck() {
        NewStockService newStockService = new NewStockService();

        String str = newStockService.cacluNewStock(14.39,500,20);
        return str;
    }
}
