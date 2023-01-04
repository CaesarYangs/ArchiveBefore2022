//
// Created by 杨业卿 on 2020/9/27.
//


#include <iostream>

using namespace std;
const int MAXSIZE = 100;

class JosephusCircle{
public:
    JosephusCircle(){
        for(int i=0;i<MAXSIZE;i++){
            Jose[i] = 0;
        }
    }   //构造函数
    void SetCircle();   //初始化
    void PrintCircle(); //打印
    void Josephus();    //解决约瑟夫问题 数组法
    void Delete(int n);
    void Print();

private:
    int length;
    int Jose[MAXSIZE];
    int n,s,m;

};


int main(){

    JosephusCircle cir;
    cir.SetCircle();
    cir.PrintCircle();
    cir.Josephus();
    cir.Print();


    return 0;
}

void JosephusCircle ::PrintCircle() {
    for(int i=0;i<length;i++){
        cout<<Jose[i]<<" ";
    }
    cout<<endl;
}

void JosephusCircle ::SetCircle() {
    cout<<"n:";
    cin>>n;
    length = n;
    if(n==0){
        exit(0);
    }
    do{
        cout<<"s:";
        cin>>s;
    }while (s==0);

    do{
        cout<<"m:";
        cin>>m;
        if(m==0){
            cout<<"输入错误"<<endl;
        }
    }while (m==0);

    for(int i=0;i<n;i++){
        Jose[i] = i+1;
    }
}

void JosephusCircle ::Josephus() {
    int Ccount=0;
    int countNum=0;
    int del=0;
    int i=s-1;

    do{

        if(Ccount==m-1 && Jose[i]!=0){
            cout<<"出圈:"<<Jose[i]<<endl;
            Jose[i]=0;
            Ccount = 0;
            del++;

        }
        if(Jose[i]!=0){
            Ccount++;
        }

        if(i==n-1){
            i=0;
        } else{
            i++;
        }

    }while (del!=n-1);


}

void JosephusCircle ::Delete(int n) {
    if (n==length){

    } else{
        for(int i=n-1;i<length;i++){
            Jose[i] = Jose[i+1];
        }
    }
    length--;
}

void JosephusCircle ::Print() {
    cout<<"*********"<<endl;
    for(int i=0;i<n;i++){
        if(Jose[i]!=0){
            cout<<Jose[i]<<endl;
        }
    }
}

