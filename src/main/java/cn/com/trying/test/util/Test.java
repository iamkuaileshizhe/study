package cn.com.trying.test.util;


import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;

public class Test {

    public static void main(String args[]) throws InterruptedException {
//        testWhile();

        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        File[] hFiles = new File(".").listFiles(File::isHidden);

        Arrays.sort(hFiles,(File a,File b) ->{return a.compareTo(b);});
    }


    /**
    * @Title:
    * @Description:  测试break和continue的作用范围   ，结论是break和continue仅作用最近的循环
    * @param
    * @return
    * @author huxx
    * @date 2020/3/13 下午3:14
    * @update
    */
    public static void testBreakAndContinue(){
        for(int i = 0; i< 3;i++){
            System.out.println("i am first "+i);
            for( int j = 0; j<4 ;j++){
                System.out.println("i am second "+i+"-"+ j);
                if(i == 0){
                    continue;
                }
                if(i == 1){
                    break;
                }
            }
        }
    }

    public static void testWhile() {
        int i = 0;
        while (i < 3) {

            System.out.println("i am first " + i);
            for (int j = 0; j < 4; j++) {
                System.out.println("i am second " + i + "-" + j);
                if (i == 0) {
                    continue;
                }
                if (i == 1) {
                    break;
                }
            }
            i = i + 1;
            if(i == 2){
                break;
            }
        }
    }

    private static void testVector(){
        Vector cats = new Vector();
        for( int i = 0; i<10;i++){
            cats.addElement(i);
            cats.add(i+"ttt");
        }

        for(int i = 0; i< cats.size(); i++){
            Object o =  cats.get(i);
            System.out.println(o);
        }
    }

    /**
    * @Title:
    * @Description: 先进后出的集合
    * @param
    * @return
    * @author huxx
    * @date 2020/3/13 下午4:29
    * @update
    */
    private static void testStack(){
        Stack stk = new Stack();

        String[] months = {
                "January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December" };


        for(int i = 0; i < months.length; i++)
            stk.push(months[i] + " ");
        System.out.println("stk = " + stk);
        stk.addElement("The last line");
        System.out.println(
                "element 5 = " + stk.elementAt(5));
        System.out.println("popping elements:");
        while(!stk.empty())
            System.out.println(stk.pop());
    }


}
