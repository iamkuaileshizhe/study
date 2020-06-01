package cn.com.trying.stock.fund.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
* @Title: FundBean
* @Description: 资金对象
* @author huxx
* @date 2020/6/1 上午9:27
* @update
*/
@Entity(name = "t_fund")
public class FundBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /**
    * @Title:
    * @Description: 操作时间
    * @author huxx
    * @date 2020/6/1 上午9:27
    * @update
    */
    private String opTime;
    /**
    * @Title:
    * @Description:  操作金额
    * @param
    * @return
    * @author huxx
    * @date 2020/6/1 上午9:24
    * @update
    */
    private double amount;
    /**
    * @Title:
    * @Description: 创建时间
    * @param
    * @return
    * @author huxx
    * @date 2020/6/1 上午9:39
    * @update
    */
    private String createTime;

    /**
    * @Title:
    * @Description: 总金额
    * @author huxx
    * @date 2020/6/1 上午9:25
    * @update
    */
    private double totalAmount;


    public long getId() {
        return id;
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
