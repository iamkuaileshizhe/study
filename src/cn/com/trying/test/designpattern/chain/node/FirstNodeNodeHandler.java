package cn.com.trying.test.designpattern.chain.node;

import cn.com.trying.test.designpattern.chain.bean.IStudent;

/**
 * 第一个节点处理类
 */
public class FirstNodeNodeHandler extends AbstractNodeHandler {
    public FirstNodeNodeHandler(){
        super(1);
    }
    @Override
    public void hanldeRequest(IStudent student) {
        System.out.println("第一个节点处理："+student.getResponseMessage());
    }
}
