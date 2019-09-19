package cn.com.trying.test.designpattern.chain.node;

import cn.com.trying.test.designpattern.chain.bean.IStudent;

/**
 * 处理节点抽象类
 */
public abstract class AbstractNodeHandler implements INodeHandler {
    private INodeHandler nextNodeHandler ;
    private int state;
    public AbstractNodeHandler(int state){
        this.state = state;
    }

    public abstract void hanldeRequest(IStudent student);


    @Override
    public void setNextNodeHandler(INodeHandler nextNodeHandler) {
        this.nextNodeHandler = nextNodeHandler;
    }
    @Override
    public void handle(IStudent student){
        if(student != null){
            if(this.state == -1 || this.state == student.getState()){
                this.hanldeRequest(student);
            }else{
                this.nextNodeHandler.handle(student);
            }
        }
    }


}
