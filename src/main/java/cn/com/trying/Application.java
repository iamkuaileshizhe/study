package cn.com.trying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application  {
    public static void main(String[] args) {
        System.out.println("-------------------"+System.getProperty("os.name"));
        SpringApplication.run(Application.class, args);
    }
}
