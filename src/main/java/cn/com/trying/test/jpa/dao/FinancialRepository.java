package cn.com.trying.test.jpa.dao;

import cn.com.trying.test.jpa.bean.Financial;
import cn.com.trying.test.jpa.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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

    List<Financial>  findAllByCode(String code);
}
