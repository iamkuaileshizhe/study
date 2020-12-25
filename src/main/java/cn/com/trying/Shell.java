package cn.com.trying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Shell {
    public static void main(String[] args) {
        System.out.println("-------------------"+System.getProperty("os.name"));
        String command = "java -version ";

        test(command);

    }

    public static void test(String command) {
        Process process = null;

        try {
            String[] cmd = { "/bin/sh", "-c", command };
            process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader( new InputStreamReader(  process.getInputStream()),1024);
            String data = "";
            while((data = reader.readLine()) != null) {
                System.out.println(data);
            }

            int exitValue = process.waitFor();

            if(exitValue != 0) {
                System.out.println("error");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}



