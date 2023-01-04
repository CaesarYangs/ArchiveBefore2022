//
// Created by 杨业卿 on 2020/10/4.
//

#ifndef HOMEWORK_CLASSEXP_1_HEADER_ROUTE_H
#define HOMEWORK_CLASSEXP_1_HEADER_ROUTE_H

#include <string>
#include <fstream>
using namespace std;
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
    bool Full;

    NodeR* next=NULL;    //节点指针域

};

class Route{
public:
    Route(){
        head = NULL;
        length = 0;
    }   //构造函数
    void SetList();     //创建链表
    void SaveToFile();
    void LoadFromFIle();
    void PrintList();   //打印链表
    void PrintOne(NodeR* ptr);
    void Add();

    NodeR* FindFSN();
    void FindCity();
    void FindCoupon();

    void ChangeFlight();


private:
    NodeR* head; //头指针
    int length; //链表长

};

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
        cout<<"Full?";
        cin>>p->Full;


        pre->next = p;
        pre = p;

        cout<<endl;
        cout<<"continue? 1/0";
        cin>>end;
    }while (end==1);

}

void Route ::SaveToFile() {
    ofstream out("/Users/yyq/Downloads/flight.txt",ios::app);
    if(!out){
        cout<<"can not open"<<endl;
        return;
    }
    NodeR* ptr = head->next;

    do{
        out<<ptr->SN<<" ";
        out<<ptr->DCity<<"->";
        out<<ptr->ACity<<" ";
        out<<ptr->OffTime<<" ";
        out<<ptr->LandTime<<" ";
        out<<ptr->price<<" ";
        out<<ptr->coupon<<" ";
        out<<ptr->Full<<" ";

        out<<endl;


        ptr = ptr->next;
    }while (ptr!=NULL);
    cout<<"saved!"<<endl;
    out.close();
}

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
        in>>p->Full;

        pre->next = p;
        pre = p;
        p->next=NULL;

        //delete p;

    }while (in.peek()!=EOF);
    in.close();
}

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
        //cout<<ptr->Full<<" ";

        ptr = ptr->next;
        cout<<endl;
    }while (ptr!=NULL);

}

void Route ::PrintOne(NodeR *ptr) {
    cout<<ptr->SN<<" ";
    cout<<ptr->DCity<<" ";
    cout<<ptr->ACity<<" ";
    cout<<ptr->OffTime<<" ";
    cout<<ptr->LandTime<<" ";
    cout<<ptr->price<<" ";
    cout<<ptr->coupon<<" ";
    //cout<<ptr->Full<<" ";

    cout<<endl;
}

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
}

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

}

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
}

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
}

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
}

#endif //HOMEWORK_CLASSEXP_1_HEADER_ROUTE_H
