package cn.com.trying.test.designpattern.chain.node;

import cn.com.trying.test.designpattern.chain.bean.IStudent;

/**
 * 第二个节点处理类
 */
public class SecondNodeNodeHandler extends AbstractNodeHandler {
    public SecondNodeNodeHandler(){
        super(2);
    }
    @Override
    public void hanldeRequest(IStudent student) {
        System.out.println("第二个节点处理："+student.getResponseMessage());
    }
}