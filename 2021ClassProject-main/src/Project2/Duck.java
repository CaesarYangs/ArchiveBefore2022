package Project2;

public class Duck extends Animal implements Terrestrial{
    private int legNum = 0;

    Duck(){}
    Duck(String name,int legNum){   //构造函数
        this.name = name;
        this.legNum = legNum;
    }

    void shout(){
        System.out.print(name + "      gaga   ");
    }//重写shout叫声函数

    @Override
    public int getLegNum() {
        return legNum;
    }   //调用接口的返回腿数函数
}
