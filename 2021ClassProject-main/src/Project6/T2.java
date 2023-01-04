package Project6;
import java.io.*;
import java.lang.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class T2 {

    public static void main(String[] args) throws IOException {
        int num = 0;

        Scanner in  = new Scanner(System.in);
        System.out.print("请输入要输入的数字个数：");
        num = in.nextInt();

        List<Integer> numbers = new LinkedList();

        for(int i=1;i<=num;i++){
            System.out.print("输入第"+i+"个数：");
            numbers.add(in.nextInt());
        }
        System.out.println("输入完毕");

        float sum = 0;
        for(int i:numbers){
            sum = sum + i;
        }
        float aver = sum/num;
        System.out.println("数字的和是:"+sum);
        System.out.println("平均数是："+aver);


    }
}
