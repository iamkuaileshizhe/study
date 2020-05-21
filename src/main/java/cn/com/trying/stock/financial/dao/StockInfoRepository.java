package cn.com.trying.stock.financial.dao;

import cn.com.trying.stock.financial.bean.Financial;
import cn.com.trying.stock.financial.bean.StockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @Title: StockInfoRepository
* @Description:
* @author huxx
* @date 2019/10/21 下午3:28
* @update
*/
public interface StockInfoRepository extends JpaRepository<StockBean,Long> {
    StockBean findByCode(String code);
    StockBean findById(String id);

    @Query("select p from t_stock  p where p.code= :code and p.createTime > :time")
    List<StockBean> ByCodeAndTimeQuery(@Param("code") String code, @Param("time") String startTime);

}
