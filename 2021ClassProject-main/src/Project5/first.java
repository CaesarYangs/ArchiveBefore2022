package Project5;

import java.util.ArrayList;
import java.util.Scanner;

public class first {

    public static void main(String[] args) {
        ArrayList<String> books = new ArrayList<>();
        int i=0;
        books.add("C语言程序设计");
        books.add("java程序设计");
        books.add("Android应用");

        Scanner in = new Scanner(System.in);
        boolean mark = new Boolean(false);

        while (mark == false){
            try{
                System.out.print("(按0退出)输入数字编号：");
                i = in.nextInt()-1;
                if(i==-1){
                    break;
                }
                System.out.println(books.get(i));
                mark = true;

            }catch (IndexOutOfBoundsException a){
                System.out.println("Exception thrown  :" + a);
                System.out.println("数组下标越界");
            }
        }

    }
}
