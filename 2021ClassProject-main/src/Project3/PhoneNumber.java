package Project3;
import java.lang.*;

import java.io.IOException;
import java.util.Scanner;

public class PhoneNumber {
    public static void main(String args[]){
        String phoneNumbers[] = {"13701192543","82876650","33933"}; //输入字符串数组
        int fre[] = new int[10];

        System.out.println("原始电话号码：");
        for(String i:phoneNumbers){
            System.out.println(i);
        }

        for(int i=0;i<phoneNumbers.length;i++){
            char m[] = phoneNumbers[i].toCharArray();
            for(int j=0;j<m.length;j++){    //计算每一个数字的出现频率
                int mid = m[j];
                ++fre[mid-48];
            }

        }

        System.out.println("各数字在电话号码中出现频率:");
        int max = 0;
        int maxi = 0;
        for(int i=0;i<fre.length;i++){  //判断最大数以及以及出现频率
            if(fre[i]>max){
                max = fre[i];
                maxi = i;
            }
            System.out.print(i+":"+fre[i]+"  ");
        }
        System.out.println();
        System.out.println("max="+maxi+":"+max);

        //String re = String.valueOf(maxi);
        char re = (char) (maxi+48); //转换为字符
        System.out.println("互换后的电话号码:");
        for(int i=0;i<phoneNumbers.length;i++){
            System.out.println(phoneNumbers[i].replace(re, '8')+" ");   //替换并显示
        }

    }
}
