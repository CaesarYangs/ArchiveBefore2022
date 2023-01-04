//
// Created by 杨业卿 on 2020/10/4.
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
    }
    void Book();
    void ShowPassenger();
    void Refund();
    void SaveToFile();
    void LoadFromFIle();

private:
    PNode* head;
};


void Passenger ::Book() {
    PNode* ptr = new PNode;
    cout<<"Enter name:";
    cin>>ptr->Name;
    cout<<"Enter ID:";
    cin>>ptr->ID;
    cout<<"Enter Booking number:";
    cin>>ptr->BookingNum;
    cout<<"Enter Flight ID:";
    cin>>ptr->Flight;
    ptr->SerialNum = rand()%10000;
    ptr->next = head;
    head = ptr;

}

void Passenger ::ShowPassenger() {
    PNode* ptr = head;
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











#endif //HOMEWORK_CLASSEXP_1_HEADER_PASSENGER_H
