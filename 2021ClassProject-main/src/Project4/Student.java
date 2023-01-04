package Project4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Student{
    String name;
    int age;
    double score;
    String classNum;

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList();
        list.add(new Student("Tom",18,100,"class05"));
        list.add(new Student("Jerry",22,70,"class04"));
        list.add(new Student("Owen",25,90,"class05"));
        list.add(new Student("Jim",30,80,"class05"));
        list.add(new Student("Steve",28,66,"class06"));
        list.add(new Student("Kevin",24,100,"class04"));

        float aver = 0;
        for(int i=0;i<list.size();i++){
            aver = aver + list.get(i).age;
        }
        aver = aver/list.size();
        System.out.println("average age:"+" "+aver);
        Double as04 = 0.0 ,as05 = 0.0,as06=0.0;

        ArrayList<Student> listcla4 = new ArrayList();
        ArrayList<Student> listcla5 = new ArrayList();
        ArrayList<Student> listcla6 = new ArrayList();
        HashMap<String,ArrayList> cla = new HashMap<String,ArrayList>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).classNum=="class05"){
                listcla5.add(list.get(i));
                as05 = as05+list.get(i).score;
            }else if(list.get(i).classNum=="class04"){
                listcla4.add(list.get(i));
                as04 = as04+list.get(i).score;
            }else {
                listcla6.add(list.get(i));
                as06 = as06+list.get(i).score;
            }
        }

        as04 = as04/listcla4.size();
        as05 = as05/listcla5.size();
        as06 = as06/listcla6.size();

        cla.put("class04",listcla4);
        cla.put("class05",listcla5);
        cla.put("class06",listcla6);
        //System.out.println(cla);


        System.out.println("class05"+" "+as05);
        System.out.println("class04"+" "+as04);
        System.out.println("class06"+" "+as06);
    }





    Student(String n,int a,double s,String c){
        name = n;
        age = a;
        score = s;
        classNum = c;
    }

}