package cn.com.trying.test.designpattern.chain.node;

import cn.com.trying.test.designpattern.chain.bean.IStudent;

/**
 * 第三个节点处理类
 */
public class LastNodeNodeHandler extends AbstractNodeHandler {
    public LastNodeNodeHandler(){
        super(-1);
    }
    @Override
    public void hanldeRequest(IStudent student) {
        System.out.println("第四个节点处理："+student.getResponseMessage());
    }
}
