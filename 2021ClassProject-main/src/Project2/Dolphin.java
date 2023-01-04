package Project2;

public class Dolphin extends Animal implements Terrestrial{
    Dolphin(){}
    Dolphin(String name){   //构造函数
        this.name = name;
    }

    void shout(){
        System.out.print(name+"   dolphin");
    }//重写shout叫声函数

    @Override
    public int getLegNum() {
        return 0;
    }   //调用接口的返回腿数函数


}
