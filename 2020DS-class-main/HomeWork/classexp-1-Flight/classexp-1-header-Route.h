//
// Created by 杨业卿 on 2020/10/4.
//

#ifndef HOMEWORK_CLASSEXP_1_HEADER_ROUTE_H
#define HOMEWORK_CLASSEXP_1_HEADER_ROUTE_H

#include <string>
#include <fstream>
using namespace std;
class Passenger;
class Route;
class NodeR{
public:
    friend class Route;  //使用友元类的方式组合数据类和操作类
private:
    char data='n';  //节点数据域

    string SN="0";
    string OffTime="0";
    string LandTime="0";
    string DCity="0";
    string ACity="0";
    double price;
    double coupon;
    int restTicket;
    bool Full;

    NodeR* next=NULL;    //节点指针域

};

class Route{
public:
    friend class Passenger;
    Route(){
        head = NULL;
        length = 0;
    }   //构造函数
    void SetList();     //创建链表
    void SaveToFile();  //存储到文件
    void LoadFromFIle();    //从文件调用
    void PrintList();   //打印链表
    void PrintOne(NodeR* ptr);  //单独打印函数 只打印一个节点的内容
    void Add(); //手动追加函数

    void Find();    //总查找函数
    NodeR* FindFSN();   //根据航班编号查找
    NodeR* UFindSN(string code);
    void FindCity();    //按抵达活出发城市查找
    void FindCoupon();  //查找有无优惠

    bool JudgeBuy(NodeR* ptr,int num);  //判断是否能执行购买函数
    void ChangeNum(int num,NodeR* ptr); //将航班剩余票数--
    void ChangeNumplus(int num,NodeR* ptr); //将航班剩余票数++

    void ChangeFlight();    //改变航班信息


private:
    NodeR* head; //头指针
    int length; //链表长

};  //Route类 存储全部航班信息以及操作方法借口

void Route ::SetList() {
    NodeR *p = new NodeR;
    head = p;
    p->data = '0';
    NodeR *pre = head;
    bool end=1;

    do{
        p = new NodeR;

        cout<<"SN:";
        cin>>p->SN;
        cout<<"Offtime:";
        cin>>p->OffTime;
        cout<<"LandTime:";
        cin>>p->LandTime;
        cout<<"DCity";
        cin>>p->DCity;
        cout<<"ACity";
        cin>>p->ACity;
        cout<<"Price";
        cin>>p->price;
        cout<<"Coupon";
        cin>>p->coupon;
        cout<<"Rest Ticket:";
        cin>>p->restTicket;


        pre->next = p;
        pre = p;

        cout<<endl;
        cout<<"continue? 1/0";
        cin>>end;
    }while (end==1);

}   //建立链表

void Route ::SaveToFile() {
    ofstream out("/Users/yyq/Downloads/flight.txt",ios::app);
    if(!out){
        cout<<"can not open"<<endl;
        return;
    }
    NodeR* ptr = head->next;

    do{
        out<<endl;
        out<<ptr->SN<<" ";
        out<<ptr->DCity<<" ";
        out<<ptr->ACity<<" ";
        out<<ptr->OffTime<<" ";
        out<<ptr->LandTime<<" ";
        out<<ptr->price<<" ";
        out<<ptr->coupon<<" ";
        out<<ptr->restTicket<<" ";

        out<<endl;


        ptr = ptr->next;
    }while (ptr!=NULL);
    cout<<"saved!"<<endl;
    out.close();
}   //保存航班信息到文件

void Route ::LoadFromFIle() {
    ifstream in("/Users/yyq/Downloads/flight.txt",ios::in);
    if(!in){
        cout<<"can not find file"<<endl;
        return;
    }
    NodeR* p = new NodeR;
    head = p;
    p->data = '0';
    NodeR *pre = head;

    do{
        NodeR* p = new NodeR;

        in>>p->SN;
        in>>p->DCity;
        in>>p->ACity;
        in>>p->OffTime;
        in>>p->LandTime;
        in>>p->price;
        in>>p->coupon;
        in>>p->restTicket;

        pre->next = p;
        pre = p;
        p->next=NULL;

        //delete p;

    }while (in.peek()!=EOF);
    in.close();
}   //从文件加载航班信息到内存

void Route ::PrintList() {
    NodeR* ptr=head->next;

    do{
        cout<<ptr->SN<<" ";
        cout<<ptr->DCity<<" ";
        cout<<ptr->ACity<<" ";
        cout<<ptr->OffTime<<" ";
        cout<<ptr->LandTime<<" ";
        cout<<ptr->price<<" ";
        cout<<ptr->coupon<<" ";
        cout<<ptr->restTicket;
        //cout<<ptr->Full<<" ";

        ptr = ptr->next;
        cout<<endl;
    }while (ptr!=NULL);

}   //打印整个链表

void Route ::PrintOne(NodeR *ptr) {
    cout<<ptr->SN<<" ";
    cout<<ptr->DCity<<" ";
    cout<<ptr->ACity<<" ";
    cout<<ptr->OffTime<<" ";
    cout<<ptr->LandTime<<" ";
    cout<<ptr->price<<" ";
    cout<<ptr->coupon<<" ";
    cout<<ptr->restTicket;
    //cout<<ptr->Full<<" ";

    cout<<endl;
}   //打印单独节点数据

void Route ::Add() {
    NodeR* p=head->next;
    NodeR *pre;
    do{
        if(p->next==NULL){
            p = new NodeR;

            cout<<"SN:";
            cin>>p->SN;
            cout<<"Offtime:";
            cin>>p->OffTime;
            cout<<"LandTime:";
            cin>>p->LandTime;
            cout<<"DCity";
            cin>>p->DCity;
            cout<<"ACity";
            cin>>p->ACity;
            cout<<"Price";
            cin>>p->price;
            cout<<"Coupon";
            cin>>p->coupon;

            pre->next = p;
            p->next = NULL;

        }
        pre = p;
        p = p->next;
    }while (p!=NULL);
}   //追加信息函数

NodeR* Route ::FindFSN() {
    NodeR* ptr=head->next;
    string code;

    cout<<"enter the Unique SN Code:";
    cin>>code;

    do{
        if(ptr->SN==code){
            PrintOne(ptr);
            return ptr;
        }

        ptr = ptr->next;
    }while (ptr!=NULL);

    cout<<"##Can not find!##"<<endl;

}   //查找特定序列算法

void Route ::Find() {
    cout<<"*********"<<endl;
    int marker=1;
    string key;
    do{
        cout<<"*****************"<<endl;
        cout<<"1.SN"<<endl;
        cout<<"2.City"<<endl;
        cout<<"3.Coupon"<<endl;
        cout<<"0.end"<<endl;
        cout<<"*****************"<<endl;
        cin>>marker;

        switch (marker) {
            case 1:
                FindFSN();
                break;

            case 2:
                FindCity();
                break;

            case 3:
                FindCoupon();
                break;


        }
    }while (marker!=0);
}   //总查找算法 含用户界面

void Route ::FindCity() {
    NodeR* ptr=head->next;
    string city;
    bool key=0;

    cout<<"Enter the city:";
    cin>>city;

    do{
        if(ptr->DCity==city || ptr->ACity==city){
            PrintOne(ptr);
            key=1;
        }

        ptr = ptr->next;
    }while (ptr!=NULL);

    if(key==0){
        cout<<"##Can not find!##"<<endl;
    }
}   //查找起飞到达地点算法

void Route ::FindCoupon() {
    NodeR* ptr=head->next;
    double cou;
    bool key=0;

    cout<<"Enter the lever of coupon:";
    cin>>cou;

    do{
        if(ptr->coupon>cou || ptr->coupon==cou){
            PrintOne(ptr);
            key=1;
        }
        ptr=ptr->next;
    }while (ptr!=NULL);

    if(key==0){
        cout<<"##Can not find!##"<<endl;
    }
}   //查找优惠

void Route ::ChangeFlight() {
    NodeR* ptr = FindFSN();
    int i=0;

    cout<<"*****************************"<<endl;    //do循环负责用户界面
    cout<<"1.SN"<<endl;
    cout<<"2.DCity"<<endl;
    cout<<"3.ACity"<<endl;
    cout<<"4.OffTime"<<endl;
    cout<<"5.LandTime"<<endl;
    cout<<"6.Price"<<endl;
    cout<<"0.Exit"<<endl;
    cout<<endl;
    cin>>i;
    switch (i) {
        case 1:
            cout<<"new SN:";
            cin>>ptr->SN;
            cout<<"--Done"<<endl;
            PrintOne(ptr);
            break;

        case 2:
            cout<<"new DCity:";
            cin>>ptr->DCity;
            cout<<"--Done"<<endl;
            PrintOne(ptr);
            break;

        case 3:
            cout<<"new ACity:";
            cin>>ptr->ACity;
            cout<<"--Done"<<endl;
            PrintOne(ptr);
            break;

        case 4:
            cout<<"new OffTime:";
            cin>>ptr->OffTime;
            cout<<"--Done"<<endl;
            PrintOne(ptr);
            break;

        case 5:
            cout<<"new LandTime:";
            cin>>ptr->LandTime;
            cout<<"--Done"<<endl;
            PrintOne(ptr);
            break;

        case 6:
            cout<<"new Price:";
            cin>>ptr->price;
            cout<<"--Done"<<endl;
            PrintOne(ptr);
            break;

        default:
            break;


    }
}   //修改航班信息算法

bool Route ::JudgeBuy(NodeR* ptr,int num) {
    if(ptr->restTicket>num || ptr->restTicket==num){
        return 1;
    } else{
        return 0;
    }
}   //判断是否能

NodeR* Route ::UFindSN(string code) {
    NodeR* ptr=head->next;

    do{
        if(ptr->SN==code){
            return ptr;
        }

        ptr = ptr->next;
    }while (ptr!=NULL);

    return NULL;
}

void Route ::ChangeNum(int num,NodeR* ptr) {
    ptr->restTicket = ptr->restTicket-num;
}

void Route ::ChangeNumplus(int num, NodeR *ptr) {
    ptr->restTicket = ptr->restTicket+num;
}

#endif //HOMEWORK_CLASSEXP_1_HEADER_ROUTE_H
