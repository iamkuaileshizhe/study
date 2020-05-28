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

    /**
    * @Title:
    * @Description: 根据code、time、num查询数据
    * @param
    * @return
    * @author huxx
    * @date 2020/5/25 下午2:39
    * @update
    */
    @Query("select p from t_financial  p where p.code= :code and p.time = :time and p.num = :num")
    Financial findByCodeAndTimeAndNum(@Param("code") String code, @Param("time") String time,@Param("num") long num);

    /**
    * @Title:
    * @Description: 根据code查询每天的最新数据
    * @param
    * @return
    * @author huxx
    * @date 2020/5/25 下午2:40
    * @update
    */
    @Query(value = " SELECT f  from t_financial f  join v_financial t  on  f.code = t.code and f.time = t.time and f.num = t.num  where f.code = :code and f.time >= :startTime  and f.time <= :endTime ")
    List<Financial>  findAllMaxByCodeAndStartTimeAndEndTime(String code,String startTime,String endTime);


    /**
    * @Title:
    * @Description: 根据code查询一天的数据
    * @param
    * @return
    * @author huxx
    * @date 2020/5/25 下午2:41
    * @update
    */
    @Query("select p from t_financial  p where p.code= :code and p.time = :time ")
    List<Financial> findAllByCodeAndTime(@Param("code") String code, @Param("time") String time);
}
