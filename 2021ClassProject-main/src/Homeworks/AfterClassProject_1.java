package Homeworks;
import java.io.*;
import java.lang.*;

import java.io.IOException;
import java.util.Scanner;

public class AfterClassProject_1 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Implementation1 test = new Implementation1();
        int choice=0;
        do{
            System.out.println("0.退出");
            System.out.println("1.计算1+2+3+……+100的和");
            System.out.println("2.读入一个小于10 的整数，输出它的阶乘n!");
            System.out.println("3.求100以内所有能被3整除但是不能被5整除的数字的和");
            System.out.println("4.写一个函数，接受一个整数，输出这个整数的所有因子");
            System.out.println("5.写一个函数，接受一个整数n，输出1+2+3+……+n的和");
            System.out.println("6.写一个函数，接受一个整数n，输出它的阶乘n!");

            System.out.print("请选择：");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.println("---------------------------");
                    test.function1();
                    System.out.println("---------------------------");
                    break;
                case 2:
                    System.out.println("---------------------------");
                    test.function2();
                    System.out.println("---------------------------");
                    break;
                case 3:
                    System.out.println("---------------------------");
                    test.function3();
                    System.out.println("---------------------------");
                    break;
                case 4:
                    System.out.println("---------------------------");
                    test.function4();
                    System.out.println();
                    System.out.println("---------------------------");
                    break;
                case 5:
                    System.out.println("---------------------------");
                    test.function5();
                    System.out.println("---------------------------");
                    break;
                case 6:
                    System.out.println("---------------------------");
                    test.function6();
                    System.out.println("---------------------------");
                    break;
                case 0:
                    break;
            }

        }while (choice!=0);



    }
}

class Implementation1{  //功能实现类
    public Implementation1(){
         in = new Scanner(System.in);

    }

    Scanner in;

    public void function1(){    //计算1+2+3+……+100的和
        int result = 0;
        for(int i=1;i<101;i++){
            result = result+i;
        }
        System.out.println("1+2+3+……+100的和: "+result);
    }

    public void function2() {    //读入一个小于10 的整数，输出它的阶乘n!；
        System.out.println("请输入一个10以内的数字，由系统计算阶乘");
        System.out.println("阶乘结果："+factorial(in.nextInt()));
    }

    public int factorial(int n){
        if(n<=1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }

    public void function3(){    //求100以内所有能被3整除但是不能被5整除的数字的和
        int result=0;

        for(int i=0;i<101;i++){
            if(i%3==0 && i%5!=0){
                result = result+i;
            }
        }

        System.out.println("100以内所有能被3整除但是不能被5整除的数字的和:"+result);
    }

    public void function4(){    //写一个函数，接受一个整数，输出这个整数的所有因子
        int num = in.nextInt();
        System.out.print("因数：");
        for(int i=1;i<=num;i++){
            if(num%i==0)    //上一行的i只有能被n整除 才是n的因子
                System.out.print(i+" ");
        }
        /*for(int i=2;i<=num;i++){
            int n=i;
            while (num%n==0){
                if(judge(n)){
                    System.out.print(n+",");
                    num = num/n;
                }
            }
        }*/


    }

    public void function5(){
        int num = in.nextInt();
        int result=0;
        for(int i=1;i<=num;i++){
            result = i+result;
        }
        System.out.println("1+2+3+……+"+num+"的和: "+result);
    }

    public void function6(){    //写一个函数，接受一个整数n，输出它的阶乘n!
        System.out.println("请输入一个数字，由系统计算阶乘");
        System.out.println("阶乘结果："+factorial(in.nextInt()));
    }

    public boolean judge(int n){//判断n是否为素数
        if(n<2) return false;  //素数必须大于1；
        for(int i=2;i<n;i++){
            if(n%i ==0)
                return false;
        }
        return true;
    }
}

