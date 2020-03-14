package cn.com.trying.test.jpa.controller;

import cn.com.trying.test.jpa.bean.Person;
import cn.com.trying.test.jpa.dao.PersonRepository;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Person save(@RequestBody Map<String,String> map){
        Person p = personRepository.save(
                new Person(Long.valueOf(map.get("id")), map.get("name"), Integer.valueOf(map.get("age")), map.get("address")));
        return p;
    }

    @RequestMapping(value = "/address",method = RequestMethod.POST)
    public List<Person> address(@RequestBody Map<String,String> map){
        List<Person> people = personRepository.findByAddress( map.get("address"));
        return people;
    }

    @RequestMapping(value = "/nameAndAddress",method = RequestMethod.POST)
    public Person nameAndAddress(@RequestBody Map<String,String> map){
        Person people = personRepository
                .findByNameAndAddress( map.get("name"),  map.get("address"));
        return people;
    }

    @RequestMapping(value = "/nameAndAddressQuery",method = RequestMethod.POST)
    public  Person nameAndAddressQuery(@RequestBody Map<String,String> map){
        Person p = personRepository
                .withNameAndAddressQuery( map.get("name"),  map.get("address"));
        return p;
    }

    @ResponseBody
    @RequestMapping(value = "/nameAndAddressNamedQuery",method = RequestMethod.GET)
    public Person nameAndAddressNamedQuery( ){
        Map<String,String> map = Maps.newHashMap();
        map.put("name","333");
        map.put("adress","dfds");
        Person p = personRepository
                .withNameAndAddressNamedQuery(map.get("name"),  map.get("address"));
        return p;
    }

    @RequestMapping(value = "/sort",method = RequestMethod.POST)
    public List<Person> sort(){
        List<Person> people = personRepository
                .findAll(new Sort(Sort.Direction.ASC,"age"));
        return people;
    }

    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public Page<Person> page(){
        Page<Person> pagePeople = personRepository
                .findAll(new PageRequest(1, 2));
        return pagePeople;
    }
}
