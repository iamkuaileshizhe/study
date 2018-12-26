package cn.com.trying.test.designpattern.chain.node;

import cn.com.trying.test.designpattern.chain.bean.IStudent;

/**
 * 责任链处理接口
 */
public interface INodeHandler {
    public void handle(IStudent student);

    public void setNextNodeHandler(INodeHandler handler);
}
