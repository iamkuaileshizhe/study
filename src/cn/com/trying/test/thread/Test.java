package cn.com.trying.test.thread;

import java.util.concurrent.*;

public class Test {
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
    private ScheduledExecutorService singleScheduledThreadPool = Executors.newSingleThreadScheduledExecutor(threadFactory);
    private ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
    private ExecutorService fixThreadPool = Executors.newFixedThreadPool(3);

    private String threadName;

    public Test(){
        setThreadName("线程测试1-");
    }
    public Test(String threadName){
        setThreadName(threadName+"-");
    }

    /**
     *多个线程的时候是排队进行执行
     */
    public void testForSingeScheduledThread(){
        TestForScheduledThread testForScheduledThread1 = new TestForScheduledThread();
        testForScheduledThread1.setName(getThreadName()+"线程1");
        singleScheduledThreadPool.scheduleAtFixedRate(testForScheduledThread1,0,5, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程1被启动");

        TestForScheduledThread testForScheduledThread2 = new TestForScheduledThread();
        testForScheduledThread2.setName(getThreadName()+"线程2");
        singleScheduledThreadPool.scheduleAtFixedRate(testForScheduledThread2,0,5, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程2被启动");

    }


    /**
     * 在线程个数小于线程池大小的时候并行处理，当大于线程池大小的时候线程将排队执行
     */
    public void testForScheduledThread(){
        TestForScheduledThread testForScheduledThread = new TestForScheduledThread();
        testForScheduledThread.setName(getThreadName()+"线程1");
        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread,0,5, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程1被启动");

        TestForScheduledThread testForScheduledThread2 = new TestForScheduledThread();
        testForScheduledThread2.setName(getThreadName()+"线程2");
        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread2,0,5, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程2被启动");

        TestForScheduledThread testForScheduledThread3 = new TestForScheduledThread();
        testForScheduledThread3.setName(getThreadName()+"线程3");
        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread3,0,5, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程3被启动");

        TestForScheduledThread testForScheduledThread4 = new TestForScheduledThread();
        testForScheduledThread4.setName(getThreadName()+"线程4");
        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread4,0,5, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程4被启动");

    }

    public void testForCallable(Long timeout){
        TestThread testThread = new TestThread("我就是测试下啊");
        Future<String> future = singleScheduledThreadPool.schedule(testThread,0,TimeUnit.SECONDS);
        try {
            //get会阻塞当前进程，一直到线程结束
            String dd = future.get(timeout,TimeUnit.SECONDS);
            System.out.println("....................."+dd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        if(future.isDone()){
            System.out.println("....................我执行完了"+future);
        }
        if(future.isCancelled()){
            System.out.println("----------------------我被取消执行了");
        }

    }


    public static void main(String args[]) {
        Test test = new Test();
        Test test2 = new Test("线程测试2");
        //每线程对象的作用范围是对象级别的。即下例所示的同一个类的不同对象的线程池相互独立
//       test.testForScheduledThread();
//        test2.testForScheduledThread();

//        test.testForSingeScheduledThread();
//        test2.testForSingeScheduledThread();

        test.testForCallable(5L);




    }






    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
