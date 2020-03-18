package cn.com.trying.test.util;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @Title: StreamTest
* @Description: java中流的相关操作测试类
* @author huxx
* @date 2020/3/16 下午2:51
* @update
*/
public class StreamTest {

    public static void main(String args[]) {
        List<Apple> appleList = Lists.newArrayList();
        Apple apple1 = new Apple("green","1",100);
        Apple apple2 = new Apple("green","2",20);
        Apple apple3 = new Apple("green","3",50);
        appleList.add(apple1);
        appleList.add(apple2);
        appleList.add(apple3);

        List<String> idList = appleList.stream().filter(apple -> {
            System.out.println("filter-----------"+apple.getId());
            return  apple.getWeight() >30;
        }).map(apple ->{
            System.out.println("map--------------"+apple.getId());
            return   apple.getId();
        }).limit(2).collect(Collectors.toList());
        System.out.println(idList);


        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);



        String[] arrayOfWords = {"Hello","World"};

        //得到的是string[]的list
        List<String[]> wordList =Arrays.stream(arrayOfWords).map(word -> word.split("")).distinct().collect(Collectors.toList());

        //得到的是所有不同字母的list
        List<String> uniqueCharacters =Arrays.stream(arrayOfWords)
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());


        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println("Integer的最大值==="+max.get());
    }
}
