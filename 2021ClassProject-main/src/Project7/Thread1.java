package Project7;

import java.io.IOException;

class driver extends Thread{
    public void run(){
        System.out.println("货运司机开始工作---");
        for(int i=0;i<3;i++){
            System.out.println("货运司机开车");
        }
    }

}

class worker extends Thread{
    public void run(){
        System.out.println("装配工人开始工作---");
        for(int i=0;i<3;i++){
            System.out.println("装配工人正在工作");
        }
    }



}

class boxmanager extends Thread{
    public void run(){
        System.out.println("仓库管理员开始工作---");
        for(int i=0;i<3;i++){
            System.out.println("仓库管理员打开仓库");
        }
    }


}

public class Thread1{

    public static void main(String[] args) throws IOException {

        driver d = new driver();
        worker w = new worker();
        boxmanager bm = new boxmanager();


        d.start();
        bm.start();
        w.start();
        try{
            bm.join();

        }catch (InterruptedException e){

        }
        try{
            w.join();

        }catch (InterruptedException e){

        }



    }

}
