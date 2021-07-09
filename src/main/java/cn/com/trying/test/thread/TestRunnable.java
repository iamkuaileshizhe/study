package cn.com.trying.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestRunnable implements Runnable {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //volatile修饰的变量，每次读取都会去主内存读取共享变量的最新值，然后复制到线程自己的工作空间；线程工作内存中的变量修改后，会立即同步到主内存中。
    public volatile   static String  flag = "";
    public   static AtomicBoolean isWait = new AtomicBoolean(false);
    @Override
    public void run()  {
        Thread thread = Thread.currentThread();
        String threadName =  thread.getName();
        String threadGroupName = thread.getThreadGroup().getName();
        //使进行阻塞10方便看进程的调度顺序
        try {

            String newFlag = String.format("%s-%s",threadGroupName,threadName);

            logger.info("flag的原值={}----线程[{}-{}]修改了flag的值为{}",flag,threadGroupName,threadName,newFlag);

            synchronized (isWait){
                if(isWait.get()){
                    isWait.set(false);
                    isWait.notify();
                    logger.info("线程[{}-{}]激活了线程{}",threadGroupName,threadName,flag);
                }else {
                    isWait.set(true);
                    logger.info("线程[{}-{}]调用了flag的wait方法",threadGroupName,threadName);
                    isWait.wait();
                }

            }
            flag = newFlag;

//            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            //由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
            thread.interrupt();
            e.printStackTrace();
        }finally {
            if(thread.isInterrupted()){
                logger.info("线程[{}-{}]----被中断退出拉",threadGroupName,threadName);
            }else{
                logger.info("线程[{}-{}]----完成工作退出拉",threadGroupName,threadName);
            }

        }

    }
}
