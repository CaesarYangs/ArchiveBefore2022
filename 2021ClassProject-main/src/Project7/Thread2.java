package Project7;

class sales{
    private int money = 5;
    private int price = 5;
    public void addMoney(){
        money += price;
    }
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean canBuyTicket(int money){
        if (this.money >= money - price){
            return true;
        }else{
            return false;
        }
    }
}

class ticket implements Runnable {
    private static int num = 3;
    private static int price =5;
    private static int money = 15;
    private static int them = 0;
    //private static int moneynum =3;


    public ticket(int m){
        them = m;
    }

    public boolean canBuyTicket(){
        if (money>=them-price && num>0){
            return true;
        }else{
            return false;
        }
    }


    public synchronized void run(){
        if(canBuyTicket()){
            /*ry {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            money +=price;
            num--;
            System.out.println(Thread.currentThread().getName() + ":买到一张票！");
        }
    }
}

public class Thread2 {
    public static void main(String[] args) {
        ticket t1 = new ticket(20);
        ticket t2 = new ticket(10);
        ticket t3 = new ticket(5);

        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        Thread T3 = new Thread(t3);
        T1.setName("张");
        T2.setName("李");
        T3.setName("赵");
        T1.start();
        T2.start();
        T3.start();
        //ticket t4 = new ticket(0);//


    }

}
