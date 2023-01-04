package Project2;

class Cat extends Animal implements Terrestrial  {
    private int legNum=0;

    Cat(){}
    Cat(String name,int legNum){    //构造函数
        this.name = name;
        this.legNum = legNum;
    }

    void shout(){   //重写shout叫声函数
        System.out.print(name+"       miao   ");
    }

    @Override
    public int getLegNum() {
        return legNum;
    }   //调用接口的返回腿数函数
}
