//
// Created by 杨业卿 on 2020/9/21.
//


#include <iostream>

using namespace std;
const int MAXSIZE = 50;

class SeqList{
public:
    SeqList();  //设置数组
    int Length();   //返回数组长度
    char Get(int n);    //返回数组某个值
    void Insert(int n); //向数组插入元素
    void Delete(int n); //删除指定元素
    void FindI(int n);  //按序号查找
    void FindV(char value); //按值查找
    void DisplayAll();  //显示数组全部内容



private:
    char chain[MAXSIZE];    //静态数组
    int length;     //数组长度

};



int main(){

    SeqList list;   //声明SeqList类对象

    int marker;

    do{
        cout<<"*****************************"<<endl;    //do循环负责用户界面
        cout<<"1.显示数组内容"<<endl;
        cout<<"2.插入元素"<<endl;
        cout<<"3.删除元素"<<endl;
        cout<<"4.获得当前数组长度"<<endl;
        cout<<"5.按序号查找"<<endl;
        cout<<"6.按值查找"<<endl;
        cout<<"0.Exit"<<endl;
        cout<<endl;

        int n=0;
        cin>>marker;
        switch (marker) {
            case 1:
                list.DisplayAll();
                break;

            case 2:
                cout<<"输入插入的位置:";
                cin>>n;
                list.Insert(n);
                list.DisplayAll();
                break;

            case 3:
                cout<<"输入删除的位置:";
                cin>>n;
                list.Delete(n);
                list.DisplayAll();
                break;

            case 4:
                cout<<"数组长度："<<list.Length()<<endl;
                break;

            case 5:
                cout<<"输入要查找的序号:";
                cin>>n;
                list.FindI(n);
                break;

            case 6:
                char np;
                cout<<"输入要查找的值:";
                cin>>np;
                list.FindV(np);
                break;

        }




    }while (marker != 0);

    return 0;
}

SeqList :: SeqList() {

    chain[0] = 'a';
    chain[1] = 'b';
    chain[2] = 'c';
    chain[3] = 'd';
    chain[4] = 'e';
    length = 5;

}   //构造函数 构造5个数据元素的数组

void SeqList :: DisplayAll() {
    for(int i=0;i<length;i++){
        cout<<chain[i]<<" ";

    }
    cout<<endl;
}   //显示数组全部数据

int SeqList :: Length() {
    return length;
}   //返回数组长度

char SeqList :: Get(int n) {
    if(n>length){
        cout<<"下标越界"<<endl;
    } else{
        return chain[n-1];
    }
}   //

void SeqList :: FindI(int n) {
    if(n>length){
        cout<<"下标越界"<<endl;
    } else{
        cout<<chain[n-1]<<endl;
    }
}   //按序号查找

void SeqList ::FindV(char value) {
    for (int i=0;i<length;i++){
        if (chain[i]==value){
            cout<<"值："<<value<<" "<<"位置："<<i+1<<endl;
            return;
        }
    }
    cout<<"未找到"<<endl;
}   //按值查找

void SeqList :: Insert(int n) {
    if(n>length){
        cout<<"下标越界"<<endl;
        return;
    }

    cout<<"Enter (char)";
    char in;
    cin>>in;
    length++;

    for (int i=length-1;i>n-1;i--){
        chain[i+1] = chain[i];
    }
    chain[n] = in;

}   //插入

void SeqList :: Delete(int n) {
    if(n>length){
        cout<<"下标越界"<<endl;
        return;
    }
    for(int i=n-1;i<length;i++){
        chain[i] = chain[i+1];
    }
    length--;

}   //删除