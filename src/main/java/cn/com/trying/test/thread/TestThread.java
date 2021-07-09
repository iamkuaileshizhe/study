package cn.com.trying.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TestThread implements Callable<String> {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public volatile  static String  flag = "";
    private String name = "";

    public TestThread(){
        setName("");
    }
    public TestThread(String name){
        setName(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        //使进行阻塞10方便看进程的调度顺序
        try {
            Thread thread = Thread.currentThread();
            String threadName =  thread.getName();
            String threadGroupName = thread.getThreadGroup().getName();
            String newFlag = String.format("{}-{}",threadGroupName,threadName);
            logger.info("flag的原值={}----线程[{}-{}]修改了flag的值为{}",flag,threadGroupName,threadName,newFlag);
            flag = newFlag;

            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getName();
    }
}
