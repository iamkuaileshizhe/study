package cn.com.trying.test.thread;

import java.util.concurrent.Callable;

public class TestThread implements Callable<String> {
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
        System.out.println("-------------------------------TestForScheduledThread------"+name);
        //使进行阻塞10方便看进程的调度顺序
        try {
            Thread.sleep(10*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getName();
    }
}
