package cn.com.trying.test.designpattern.chain;

import cn.com.trying.test.designpattern.chain.bean.IStudent;
import cn.com.trying.test.designpattern.chain.bean.Student;
import cn.com.trying.test.designpattern.chain.node.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *  构建责任链信息
 *  TODO 责任链的处理节点使用List进行管理，然后根据list进行责任链的顺序构建
 */
public class ChainManger {
    private INodeHandler firstNodeHanlder;
    private  INodeHandler secondNodeHanlder;
    private  INodeHandler threeNodeHanlder;
    private INodeHandler lastNodeHandler;
    private List<INodeHandler> nodeList = new ArrayList<INodeHandler>();
    //单例模式
    private  static ChainManger chainManger =  new ChainManger();

    public ChainManger(){
         this.firstNodeHanlder = new FirstNodeNodeHandler();
         this.secondNodeHanlder = new SecondNodeNodeHandler();
         this.threeNodeHanlder = new ThreeNodeNodeHandler();
         this.lastNodeHandler = new LastNodeNodeHandler();
         nodeList.add(this.firstNodeHanlder);
         nodeList.add(this.secondNodeHanlder);
         nodeList.add(this.threeNodeHanlder);
         nodeList.add(this.lastNodeHandler);

         //建立责任链
        Iterator<INodeHandler> iterator = nodeList.iterator();
        INodeHandler temp = null;
        while (iterator.hasNext()){
            if(temp == null){
                temp = iterator.next();
            }else{
                INodeHandler temp1 = iterator.next();
                temp.setNextNodeHandler(temp1);
                temp = temp1;
            }
        }
//        for(int i = 1 ; i< nodeList.size();i++){
//            nodeList.get(i-1).setNextNodeHandler(nodeList.get(i));
//        }
//         this.firstNodeHanlder.setNextNodeHandler(this.secondNodeHanlder);
//         this.secondNodeHanlder.setNextNodeHandler(this.threeNodeHanlder);
//         this.threeNodeHanlder.setNextNodeHandler(this.lastNodeHandler);
    }

    public void sendMessage(IStudent student){
//        this.firstNodeHanlder.handle(student);
        this.nodeList.get(0).handle(student);
    }

    public static void main(String[] arg){
        for(int i = 1 ;i<9;i++){
            IStudent student = new Student(i,"学生"+i+"想要请假");
            ChainManger.chainManger.sendMessage(student);
        }
    }
}
