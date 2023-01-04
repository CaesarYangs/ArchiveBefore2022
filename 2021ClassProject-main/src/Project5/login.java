package Project5;

import java.util.Scanner;

public class login {
    public static String Username;
    public static String Password;

    public static void Login()throws RE{
        Scanner in = new Scanner(System.in);
        System.out.print("用户名：");
        String u = in.nextLine();
        System.out.print("密码：");
        String p = in.nextLine();

        if(Username.equals(u)&&Password.equals(p)){
            System.out.println("--------------密码正确 登陆成功--------------");
        }else {
            throw new RE(u,p);
        }
    }

    public static void main(String[] args) {
        Username = "admin";
        Password = "111111";

        try{
            Login();
        }catch (RE r){
            System.out.print("Exception thrown  :");
            System.out.println("用户名或密码错误");
        }

    }

}

class RE extends Exception{
    private String user;
    private String pass;

    public RE(String user,String pass){
        this.user = user;
        this.pass = pass;
    }


}
