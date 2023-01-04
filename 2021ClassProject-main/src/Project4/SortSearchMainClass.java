package Project4;
import java.io.*;
import java.lang.*;
import java.util.*;

public class SortSearchMainClass {

    public static void main(String[] args) {
        LinkedList<Book> books = new LinkedList<Book>();
        books.add(new Book(29.0,"Java"));
        books.add(new Book(19.9,"算法导论"));
        books.add(new Book(32.0,"数据库教程"));
        books.add(new Book(35.9,"数据结构与算法"));
        books.add(new Book(19.9,"机器学习"));
        books.add(new Book(19.9,"深度学习"));


        /*Iterator<Book> it = books.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().bookname+" "+it.next().price);
        }*/



        System.out.println("全部图书：");
        for(int i=0;i<books.size();i++){
            System.out.println(books.get(i).bookname+" "+books.get(i).price);
        }

        System.out.println();
        Collections.sort(books);
        System.out.println("全部图书--排序后：");
        for(int i=0;i<books.size();i++){
            System.out.println(books.get(i).bookname+" "+books.get(i).price);
        }
        System.out.println();
        System.out.println();
        System.out.println("价格相同：");
        Book b = new Book(19.9,"c");
        for(int i=0;i<books.size();i++){
            if(b.price == books.get(i).price){
                System.out.println(books.get(i).bookname+" "+books.get(i).price);
            }
        }


    }
}


class Book implements Comparable<Book>{
    Book(double p,String name){
        price = p;
        bookname = name;
    }
    public void dis(){
        System.out.println(bookname+" "+price);
    }
    double price;
    String bookname;

    public int compareTo(Book book){
        if(book.price<price){
            return -1;
        }else if(book.price==price){
            return 0;
        }else {
            return 1;
        }

    }
}