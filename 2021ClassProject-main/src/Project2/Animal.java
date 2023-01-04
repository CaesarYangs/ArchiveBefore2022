package Project2;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Animal implements Terrestrial{ //Animal类设计
    String name;

    Animal(){}
    Animal(String name){    //构造器
        this.name = name;
    }

    public String getName(){    //返回名字函数
        return name;
    }

    void shout(){}  //继承的动物叫函数

    @Override
    public int getLegNum() {    //从接口派生的返回动物腿数的函数
        return 0;
    }

    public static void main(String[] args) throws IOException { //主函数
        ArrayList<Animal> array = new ArrayList<>();
        Cat c = new Cat("Cat",4);   //创建三种动物的实例化对象
        Duck duck = new Duck("Duck",2);
        Dolphin dolphin = new Dolphin("Dolphin");

        array.add(c);   //将三个对象放入对象数组中 方便操作
        array.add(duck);
        array.add(dolphin);


        System.out.println("Name      Shout     Legs");
        System.out.println("------------------------");
        for (Animal i:array){   //遍历数组 进行操作
            i.shout();
            if(i instanceof Cat){
                System.out.println("   "+i.getLegNum());
            }
            if(i instanceof Duck){
                System.out.println("   "+i.getLegNum());
            }
            if(i instanceof Dolphin){
                System.out.println("   "+i.getLegNum());
            }
            //System.out.println("   "+i.getLegNum());
        }



    }

}
