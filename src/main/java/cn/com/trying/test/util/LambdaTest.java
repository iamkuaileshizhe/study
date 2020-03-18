package cn.com.trying.test.util;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest {
    public static void main(String args[]) {
        //查找隐藏文件的两种写法
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        File[] hFiles = new File(".").listFiles(File::isHidden);

        Arrays.sort(hFiles,(File a, File b) ->{return a.compareTo(b);});

        //---------------------------------------------------------------------------------
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
        System.out.println(stringOp((String str) -> str.length()+"---"+str.trim(),"ddddd"));
        String testVar = "i am a variable";
        System.out.println(handleStr((String str) -> str.length()+"---"+testVar,"ddddd"));

        System.out.println(handleStr(LambdaTest::test,"ddddd"));


        //方法引用  lambda表达式   流   默认方法   行为参数化
    }

    public static String test(String str){
        return str.length()+"---"+str.trim();

    }
    public static String stringOp(LambdaFunc sf, String s) {
        return sf.handleString(s);
    }
    public static String handleStr(Function<String,String> f, String s){
        return  f.apply(s);
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
