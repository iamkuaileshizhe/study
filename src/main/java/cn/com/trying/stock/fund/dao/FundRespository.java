package cn.com.trying.stock.fund.dao;

import cn.com.trying.stock.fund.bean.FundBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
* @Title: FundRespository
* @Description: 资金流水持久化接口
* @author huxx
* @date 2020/6/1 上午9:29
* @update
*/
public interface FundRespository  extends JpaRepository<FundBean,Long> {

    /**
     * @Title:
     * @Description: 查询指定时间段的资金流水
     * @param
     * @return
     * @author huxx
     * @date 2020/5/25 下午2:40
     * @update
     */
    @Query(value = " SELECT f  from t_fund f where   f.opTime >= :startTime  and f.opTime <= :endTime ")
    List<FundBean> findAllByStartTimeAndEndTime( String startTime, String endTime);

}
