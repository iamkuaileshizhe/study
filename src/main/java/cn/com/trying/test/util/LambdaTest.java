package cn.com.trying.test.util;

import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String args[]) {
//        testWhile();

        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        File[] hFiles = new File(".").listFiles(File::isHidden);

        Arrays.sort(hFiles,(File a, File b) ->{return a.compareTo(b);});


        List<Apple>  list = Lists.newArrayList();
        Apple apple = new Apple();
        apple.setColor("green");
        apple.setWeight(100);
        list.add(apple);
        Apple apple1 = new Apple();
        apple1.setColor("green");
        apple1.setWeight(200);
        list.add(apple1);


        List<Apple> greenList = filterApples(list,LambdaTest::isGreenApple);
        List<Apple> heavyList = filterApples(list,LambdaTest::isHeavyApple);
        List<Apple> greenList1 = filterApples(list,(Apple a) -> "green".equals(a.getColor()));
        List<Apple> heavyList1 = filterApples(list,(Apple a) -> a.getWeight() > 150 && "green".equals(a.getColor()));
        List<Apple> heavyList3 = (List<Apple>)filter(list,(Apple a) -> a.getWeight() > 100);

       List<Apple> list4 = list.stream().filter((Apple a)-> a.getWeight() > 100).collect(Collectors.toList());
       long count = list.stream().filter((Apple a)-> a.getWeight() > 100).count();
       long count1 = list.parallelStream().filter((Apple a)-> a.getWeight() > 100).count();
        System.out.println(stringOp(LambdaTest::getStr,"ddddd"));

       List<Apple> appleList = getCopyAppleList(10000000,"");
       //3w一下三种方式效率基本一致
        //3w----70Wstream方式最快 for和iterator差不多
        ///70w---80w stream最快  for第二   iterator最慢（比stream慢一半）
        //500w一下stream最快   500w以上for最快


        List<Apple> streamList = testStream(appleList);
        List<Apple> forList = testFor(appleList);
        testFor1(appleList);
        List<Apple> iteraterList = testIterater(appleList);
        testWhile(appleList);

        //方法引用  lambda表达式   流   默认方法   行为参数化
    }
    private static List<Apple>  testStream(List<Apple> appleList){

        long curTime = System.currentTimeMillis();
        List<Apple> list = appleList.stream().filter((Apple a) -> "green".equals(a.getColor())).collect(Collectors.toList());
        long newTime = System.currentTimeMillis();
        System.out.println("stream方式--------------"+(newTime-curTime));
        return  list;
    }

    private static List<Apple>  testFor(List<Apple> appleList){
        List<Apple> list = Lists.newArrayList();
        long curTime = System.currentTimeMillis();
        for(Apple apple : appleList){
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        long newTime = System.currentTimeMillis();
        System.out.println("for方式--------------"+(newTime-curTime));
        return list;
    }

    private static List<Apple>  testFor1(List<Apple> appleList){
        List<Apple> list = Lists.newArrayList();
        long curTime = System.currentTimeMillis();
        for(int i= 0; i< appleList.size();i++){
            Apple apple = appleList.get(i);
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        long newTime = System.currentTimeMillis();
        System.out.println("for1方式--------------"+(newTime-curTime));
        return list;
    }

    private static List<Apple>  testIterater(List<Apple> appleList){
        List<Apple> list = Lists.newArrayList();
        long curTime = System.currentTimeMillis();
        Iterator<Apple> iterator = appleList.iterator();
        while(iterator.hasNext()){
             Apple apple = iterator.next();
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
        }
        long newTime = System.currentTimeMillis();
        System.out.println("iterator方式--------------"+(newTime-curTime));
        return list;
    }

    private static List<Apple>  testWhile(List<Apple> appleList){
        List<Apple> list = Lists.newArrayList();
        long curTime = System.currentTimeMillis();
        int total = appleList.size();
        int count = 0;
        while(count < total){
            Apple apple = appleList.get(count);
            if("green".equals(apple.getColor())){
                list.add(apple);
            }
            count += 1;
        }
        long newTime = System.currentTimeMillis();
        System.out.println("while方式--------------"+(newTime-curTime));
        return list;
    }

    /**
    * @Title:
    * @Description: 创建测试数据集合  注意new对象消耗比copy对象的消耗要大很多
    * @param
    * @return
    * @author huxx
    * @date 2020/3/14 上午10:17
    * @update
    */
    public static List<Apple> getCopyAppleList(int count,String color){
        long cur = System.currentTimeMillis();
        Apple p = new Apple();
        List<Apple>  list = Lists.newArrayList();
        for(int i = 0;i < count; i++){
            Apple apple =p;
            apple.setColor(color);
            list.add(apple);
        }
        long end = System.currentTimeMillis();
        System.out.println("数据创建时间----"+(end - cur)+"---"+list.size());
        return list;
    }

    public static List<Apple> getNewAppleList(int count,String color){
        long cur = System.currentTimeMillis();
        List<Apple>  list = Lists.newArrayList();
        for(int i = 0;i < count; i++){
            Apple apple = new Apple();
            apple.setColor(color);
            list.add(apple);
        }
        long end = System.currentTimeMillis();
        System.out.println("数据创建时间----"+(end - cur));
        return list;
    }

    public static String stringOp(LambdaFunc sf, String s) {
        return sf.stringFunc(s);
    }

    private  static String getStr(String str){
        int count = str.length();
        System.out.println(count);
        return count+"";
    }

    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());

    }

    public static boolean isHeavyApple(Apple apple){
        return  apple.getWeight() > 150;

    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate){
        List<Apple> list = Lists.newArrayList();

        for( Apple apple : inventory){
            if(predicate.test(apple)){
                list.add(apple);
            }
        }
        return  list;

    }

    static <T> Collection<T> filter(Collection<T> c, Predicate<T> p){
        Collection<T> collection = Lists.newArrayList();
        for(T t : c){
            if(p.test(t)){
                collection.add(t);
            }
        }

        return  collection;

    }
}
