//
// Created by yyq on 2020/10/4.
//

#ifndef HOMEWORK_CLASSEXP_1_HEADER_PASSENGER_H
#define HOMEWORK_CLASSEXP_1_HEADER_PASSENGER_H
#include <stdlib.h>
class Passenger;
class PNode{
public:
    friend class Passenger;
private:
    PNode* next=NULL;
    int SerialNum;
    string Name;
    string ID;
    int BookingNum;
    string Flight;

};

class Passenger{
public:
    Passenger(){
        head = NULL;
    }   //构造函数
    void Book(string name,string id,int num,string flight); //订票购买函数
    void ShowPassenger();   //显示乘客信息
    void SinglePrint(PNode* ptr);   //单独打印
    void Refund(string &flight,int &num);   //退款函数
    void SaveToFile();  //保存到文件中
    void LoadFromFIle();    //从文件读取
    PNode* FindSN();    //根据特定序列号查找订单
    PNode* Pre(PNode* ptr){
        PNode* p = head;
        PNode* pre;
        do{
            if(p==ptr){
                return pre;
            }
            pre = p;
            p=p->next;
        }while (p!=NULL);
    }   //根据特定指针找到前驱节点地址
    void Find();    //主查找函数

private:
    PNode* head;
    int snum=1000;
};


void Passenger ::Book(string name,string id,int num,string flight) {
    PNode* ptr = new PNode;
    ptr->Name = name;
    ptr->ID = id;
    ptr->BookingNum = num;
    ptr->Flight = flight;
    ptr->SerialNum = ++snum;
    ptr->next = head;
    head = ptr;

}

void Passenger ::ShowPassenger() {
    PNode* ptr = head;
    if(head==NULL){
        cout<<"**NO Record**"<<endl;
        return;
    }
    cout<<"------------------"<<endl;
    do{
        cout<<ptr->SerialNum<<"    ";
        cout<<ptr->Name<<"    ";
        cout<<ptr->ID<<"    ";
        cout<<ptr->Flight<<"    ";
        cout<<ptr->BookingNum<<endl;

        ptr = ptr->next;
    }while (ptr!=NULL);
    cout<<"------------------"<<endl;
}

void Passenger ::SinglePrint(PNode *ptr) {
    cout<<"------------------"<<endl;
        cout<<ptr->SerialNum<<"    ";
        cout<<ptr->Name<<"    ";
        cout<<ptr->ID<<"    ";
        cout<<ptr->Flight<<"    ";
        cout<<ptr->BookingNum<<endl;
    cout<<"------------------"<<endl;
}

void Passenger ::SaveToFile() {
    ofstream out("/Users/yyq/Downloads/passenger.txt",ios::app);
    if(!out){
        cout<<"can not open"<<endl;
        return;
    }
    PNode* ptr = head;




    do{
        out<<ptr->SerialNum<<" ";
        out<<ptr->Name<<" ";
        out<<ptr->ID<<" ";
        out<<ptr->Flight<<" ";
        out<<ptr->BookingNum<<" ";

        out<<endl;


        ptr = ptr->next;
    }while (ptr!=NULL);
    cout<<"saved!"<<endl;
    out.close();
}

void Passenger ::LoadFromFIle() {
    ifstream in("/Users/yyq/Downloads/passenger.txt",ios::in);
    if(!in){
        cout<<"can not find file"<<endl;
        return;
    }
    PNode* p = new PNode;
    head = p;
    PNode *pre = head;

    do{
        PNode* p = new PNode;

        in>>p->SerialNum;
        in>>p->Name;
        in>>p->ID;
        in>>p->Flight;
        in>>p->BookingNum;


        pre->next = p;
        pre = p;
        p->next=NULL;

        //delete p;

    }while (in.peek()!=EOF);
    in.close();
}

PNode* Passenger ::FindSN() {
    cout<<"enter the SN number:";
    int sn;
    cin>>sn;

    PNode* ptr = head;
    do{

        if(ptr->SerialNum==sn){
            SinglePrint(ptr);
            return ptr;
        }
        ptr = ptr->next;
    }while (ptr->next != NULL);
    cout<<"##Can not find!##"<<endl;
    return NULL;
}

void Passenger ::Find(){
    int marker=1;
    int ju=0;
    string key;
    do{
        cout<<"*****************"<<endl;
        cout<<"1.Name"<<endl;
        cout<<"2.ID"<<endl;
        cout<<"3.Flight"<<endl;
        cout<<"4.Serial Number"<<endl;
        cout<<"0.end"<<endl;
        cout<<"*****************"<<endl;
        cin>>marker;
        PNode* ptr = head;

        switch (marker) {
            case 1:

                cin>>key;

                do{
                    if(ptr->Name==key){
                        SinglePrint(ptr);
                        return;
                    }
                    ptr = ptr->next;
                }while (ptr->next != NULL);
                ju++;
                cout<<"##Can not find!##"<<endl;
                break;

            case 2:
                cin>>key;
                do{
                    if(ptr->ID==key){
                        SinglePrint(ptr);
                        return;
                    }
                    ptr = ptr->next;
                }while (ptr->next != NULL);
                ju++;
                cout<<"##Can not find!##"<<endl;
                break;

            case 3:
                cin>>key;
                do{
                    if(ptr->Flight==key){
                        SinglePrint(ptr);
                        return;
                    }
                    ptr = ptr->next;
                }while (ptr->next != NULL);
                cout<<"##Can not find!##"<<endl;
                break;

            case 4:
                FindSN();

        }
    }while (marker!=0);





}

void Passenger ::Refund(string &flight,int &num) {
    PNode*pre;
    PNode* ptr = FindSN();
    if(ptr==NULL){
        return;
    }
    pre = Pre(ptr);
    flight = ptr->Flight;
    num = ptr->BookingNum;

    if(ptr!=NULL){
        pre->next = ptr->next;
        delete ptr;
    }
}


#endif //HOMEWORK_CLASSEXP_1_HEADER_PASSENGER_H
