package cn.com.trying.test.designpattern.chain.node;

import cn.com.trying.test.designpattern.chain.bean.IStudent;

/**
 * 第三个节点处理类
 */
public class ThreeNodeNodeHandler  extends AbstractNodeHandler {
    public ThreeNodeNodeHandler(){
        super(3);
    }
    @Override
    public void hanldeRequest(IStudent student) {
        System.out.println("第三个节点处理："+student.getResponseMessage());
    }
}
