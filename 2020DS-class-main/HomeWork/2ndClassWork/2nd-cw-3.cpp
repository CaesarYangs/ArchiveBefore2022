//
// Created by 杨业卿 on 2020/10/19.
//


#include <iostream>
#include <string>

using namespace std;


class Node{
public:
    friend class CircleQuene;
private:
    int data;
    Node* next=NULL;

};  //节点类


class CircleQuene{
public:
    CircleQuene(){
        head = front = rear = NULL;
        Node* ptr= new Node;
        ptr->data = 0;
        ptr->next = ptr;
        head = rear = ptr;
        head->next = head;


    };
    void Print();
    void deQuene();
    void enQuene(int element);
    bool Judge();

private:
    Node* head;
    Node* front;
    Node* rear;
};


int main(){

    CircleQuene cq;
    cq.enQuene(4);  //入队
    cout<<"Quene:";
    cq.Print(); //打印队列
    cq.deQuene();   //出队
    cq.enQuene(5);
    cq.enQuene(6);
    cq.enQuene(7);
    cq.enQuene(8);
    cout<<"Quene:";
    cq.Print(); //打印队列
    cq.deQuene();   //出队
    cout<<"Quene:";
    cq.Print(); //打印队列



    return 0;
}


void CircleQuene ::Print() {
    Node* ptr = head->next;
    do{
        cout<<ptr->data<<" ";
        ptr = ptr->next;
    }while (ptr!=head);
    cout<<endl;
}

void CircleQuene ::enQuene(int element) {

    Node* ptr = new Node;
    ptr->data=element;
    rear->next = ptr;
    ptr->next = head;
    rear = ptr;

}   //入队

void CircleQuene ::deQuene() {
    Node* ptr = head->next;

    if(ptr->next!=head){
        head->next = ptr->next;
    } else{
        head->next = head;
        rear = head;
    }
    delete ptr;

}   //出队

bool CircleQuene ::Judge() {
    if(rear==head){
        return 1;
    } else{
        return 0;
    }
}   //判断队空