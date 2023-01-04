package Project5;

import com.sun.xml.internal.txw2.IllegalSignatureException;

import java.util.Scanner;

public class calculate {

    public static String op1 = new String("");
    public static String op2 = new String("");

    public static String op = new String("");


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);



        try{
            int a,b;
            System.out.println("输入op1:");
            op1 = in.nextLine();
            a = Integer.parseInt(op1);
            System.out.println("输入op2:");
            op2 = in.nextLine();
            b = Integer.parseInt(op2);
            System.out.println("输入操作符:");
            op = in.next();

            int ans;

            switch (op){
                case "+":
                    ans = (a+b);
                    System.out.print(a+"+"+b+"="+ans);
                    break;

                case "-":
                    ans = (a-b);
                    System.out.print(a+"-"+b+"="+ans);
                    break;

                case "*":
                    ans = (a*b);
                    System.out.print(a+"*"+b+"="+ans);
                    break;

                case "/":
                    if(b==0){
                        throw new ArithmeticException();
                    }else {
                        ans = (a/b);
                        System.out.print(a+"/"+b+"="+ans);
                    }
                    break;

                default:
                    throw new IllegalAccessException();
            }



        }catch (ArrayIndexOutOfBoundsException a){
            System.out.println("Exception thrown  :" + a);
        }catch (NumberFormatException n){
            System.out.println("Exception thrown  :" + "操作数输入错误");
        }catch (ArithmeticException ar){
            System.out.println("Exception thrown  :" + "分母不能为0");
        } catch (IllegalAccessException e) {
            System.out.println("Exception thrown  :错误的运算符" );
        }

    }
}
