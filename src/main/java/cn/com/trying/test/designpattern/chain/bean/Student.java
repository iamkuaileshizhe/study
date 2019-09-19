package cn.com.trying.test.designpattern.chain.bean;

public class Student implements  IStudent {
    private int state;
    private String message;
    public  Student(int state,String message){
        this.state = state;
        this.message = message;
    }
    @Override
    public int getState() {
        return this.state;
    }

    @Override
    public String getResponseMessage() {
        return this.message;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void setResponseMessage(String message) {
        this.message = message;
    }
}
