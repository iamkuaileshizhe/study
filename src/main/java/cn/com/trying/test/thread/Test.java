package cn.com.trying.test.thread;

import cn.com.trying.utils.DateUtil;

import java.util.concurrent.*;

public class Test {
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    public static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
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
    public void testForScheduledThread(long period){
        TestForScheduledThread testForScheduledThread = new TestForScheduledThread();
        testForScheduledThread.setName(getThreadName()+"线程1");
        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread,0,period, TimeUnit.SECONDS);
        System.out.println("============================"+getThreadName()+"线程1被启动");

//        TestForScheduledThread testForScheduledThread2 = new TestForScheduledThread();
//        testForScheduledThread2.setName(getThreadName()+"线程2");
//        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread2,0,5, TimeUnit.SECONDS);
//        System.out.println("============================"+getThreadName()+"线程2被启动");
//
//        TestForScheduledThread testForScheduledThread3 = new TestForScheduledThread();
//        testForScheduledThread3.setName(getThreadName()+"线程3");
//        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread3,0,5, TimeUnit.SECONDS);
//        System.out.println("============================"+getThreadName()+"线程3被启动");
//
//        TestForScheduledThread testForScheduledThread4 = new TestForScheduledThread();
//        testForScheduledThread4.setName(getThreadName()+"线程4");
//        scheduledThreadPool.scheduleAtFixedRate(testForScheduledThread4,0,5, TimeUnit.SECONDS);
//        System.out.println("============================"+getThreadName()+"线程4被启动");

    }
    /**
    * @Title:
    * @Description: 获取当前进程的所有线程
    * @param
    * @return
    * @author huxx
    * @date 2019/9/27 下午3:58
    * @update
    */
    public void getThreadCount(){
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数再加一倍，防止枚举时有可能刚好有动态线程生成
        int slackSize = topGroup.activeCount() * 2;
        Thread[] slackThreads = new Thread[slackSize];
        // 获取根线程组下的所有线程，返回的actualSize便是最终的线程数
        int actualSize = topGroup.enumerate(slackThreads);
        Thread[] atualThreads = new Thread[actualSize];
        // 复制slackThreads中有效的值到atualThreads
        System.arraycopy(slackThreads, 0, atualThreads, 0, actualSize);
        System.out.println(DateUtil.getDateTime()+"Threads size is " + atualThreads.length);
        for (Thread thread : atualThreads) {
            System.out.println("Thread name : " + thread.getName());
        }
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


    public static void main(String args[]) throws InterruptedException {
        Test test = new Test();
//        Test test2 = new Test("线程测试2");
        //每线程对象的作用范围是对象级别的。即下例所示的同一个类的不同对象的线程池相互独立
//       test.testForScheduledThread();
//        test2.testForScheduledThread();

//        test.testForSingeScheduledThread();
//        test2.testForSingeScheduledThread();

//        test.testForCallable(5L);


        Test t = new Test();
        t.testForScheduledThread(1);
        while (true){
            t.getThreadCount();
            Thread.sleep(5*1000L );
        }


    }






    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
