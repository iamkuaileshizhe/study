package cn.com.trying.stock.financial.dao;

import cn.com.trying.stock.financial.bean.Financial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
* @Title: FinancialRepository
* @Description:
* @author huxx
* @date 2019/10/21 下午3:28
* @update
*/
public interface FinancialRepository extends JpaRepository<Financial,Long> {
    List<Financial> findByCode(String code);

    @Query("select p from t_financial  p where p.code= :code and p.time > :time")
    List<Financial> ByCodeAndTimeQuery(@Param("code") String code, @Param("time") String startTime);
    @Query(value = " SELECT f  from t_financial f  join v_financial t  on  f.code = t.code and f.time = t.time and f.num = t.num  where f.code = :code   ")
    List<Financial>  findAllByCode(String code);
}
