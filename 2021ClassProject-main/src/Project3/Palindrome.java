package Project3;

import java.util.Scanner;

public class Palindrome {


    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        String s = input.next();    //输入字符串
        String rea = new StringBuffer(s).reverse().toString();  //反转字符串操作
        System.out.println(s);
        System.out.println(rea);

        if(s.equals(rea)){  //判断字符串是否相等
            System.out.println("是回文串");
        }else {
            System.out.println("不是回文串");
        }


    }

}
