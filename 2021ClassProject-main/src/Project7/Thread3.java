package Project7;

class SalesMan{
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
class BuyTicket implements Runnable{
    private int money;
    private int ticket = 0;
    private SalesMan salesMan;
    public BuyTicket(int money,SalesMan salesMan){
        this.money = money;
        this.salesMan = salesMan;
    }
    @Override
    public void run() {
        while (ticket == 0){
            synchronized (salesMan){
                if (salesMan.canBuyTicket(money)){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    salesMan.addMoney();
                    ticket++;
                    System.out.println(Thread.currentThread().getName()
                            + ":买到一张票！");
                }
            }
        }
    }
}
public class Thread3 {
    public static void main(String[] args) {
        SalesMan salesMan = new SalesMan();
        BuyTicket b1 = new BuyTicket(20,salesMan);
        BuyTicket b2 = new BuyTicket(10,salesMan);
        BuyTicket b3 = new BuyTicket(5,salesMan);
        Thread t1 = new Thread(b1);
        Thread t2 = new Thread(b2);
        Thread t3 = new Thread(b3);
        t1.setName("张");
        t2.setName("李");
        t3.setName("赵");
        t1.start();
        t2.start();
        t3.start();
    }

}
