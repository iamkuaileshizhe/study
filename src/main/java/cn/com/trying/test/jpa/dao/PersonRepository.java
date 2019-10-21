package cn.com.trying.test.jpa.dao;

import cn.com.trying.test.jpa.bean.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
* @Title: PersonRepository
* @Description:
* @author huxx
* @date 2019/10/21 下午3:28
* @update
*/
public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByAddress(String address);

    Person findByNameAndAddress(String name,String address);

    @Query("select p from Person p where p.name= :name and p.address like concat('%',:address,'%')")
    Person withNameAndAddressQuery(@Param("name")String name,@Param("address")String address);

    Person withNameAndAddressNamedQuery(String name,String address);
}
