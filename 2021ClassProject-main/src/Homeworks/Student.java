package Homeworks;
class Super{
    public void m(){
        foo();
    }
    public void foo(){
        System.out.println("foo() in Super");
    }
}
class Sub extends Super{
    public void foo(){
        System.out.println("foo() in Sub");
    } }

public class Student{
    public static void main(String args[]){
        Super s = new Sub();
        s.m(); }
}