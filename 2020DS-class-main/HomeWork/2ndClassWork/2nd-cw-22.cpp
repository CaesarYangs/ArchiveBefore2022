//
// Created by 杨业卿 on 2020/10/19.
//


#include <iostream>
#include <string>

using namespace std;
class LinkQuene;
const int MaxSize=10;

class Node{
public:
    friend class LinkQuene;
private:
    char data;
    Node* next = NULL;
};

class LinkQuene{
public:
    LinkQuene(){
        head=NULL;
        front = NULL;
        rear = NULL;
        Node* ptr = new Node;
        ptr->data = '0';
        head = ptr;
        front = head;
        rear = head;
    };
    void Set();
    void Print();
    void deQuene();
    void enQuene();
    bool Judge();
private:
    Node* head;
    Node* front;
    Node* rear;
    int length;

};

class CirQuene{
public:
    CirQuene(){
        front = rear = 0;
    };
    void Print();
    void deQuene();
    void enQuene(char element);
    bool Judge();

private:
    char CQ[MaxSize];
    int front;
    int rear;
};

int main(){

    LinkQuene lq;
    lq.enQuene();   //入队五次
    lq.enQuene();
    lq.enQuene();
    lq.enQuene();
    lq.enQuene();
    lq.Print();     //打印队列
    cout<<endl<<"deQuene 1:";
    lq.deQuene();   //出队一次
    lq.Print();
    cout<<endl<<"deQuene 1:";
    lq.deQuene();
    lq.Print();
    cout<<endl;
    cout<<"empty？"<<lq.Judge();

    /*CirQuene cq;
    cq.enQuene('a');    //入队连续五次
    cq.enQuene('b');
    cq.enQuene('c');
    cq.enQuene('d');
    cq.enQuene('e');
    cq.Print(); //打印队列
    cq.deQuene();   //出队一次
    cq.Print(); //打印队列
    cout<<cq.Judge()<<endl; //判断队列是否为空
    cq.deQuene();   //出队一次
    cq.Print(); //打印整个队列*/




    return 0;
}

void LinkQuene::Print() {
    Node* ptr=head->next;
    do{
        cout<<ptr->data<<" ";
        ptr = ptr->next;
    }while (ptr!=NULL);
}

void LinkQuene ::Set() {
    Node* ptr = new Node;
    ptr->data = '0';
    head = ptr;
}

void LinkQuene ::enQuene() {    //入队
    Node* ptr= new Node;
    cout<<"enter the data:";
    cin>>ptr->data;
    rear->next = ptr;
    rear = ptr;
}

void LinkQuene ::deQuene() {    //出队
    Node* p = front->next;
    if(p->next!=NULL){
        front->next = p->next;
    } else{
        rear = front;
    }
    delete p;
}

bool LinkQuene ::Judge() {
    if(front == rear){
        return 1;
    } else{
        return 0;
    }
}


void CirQuene ::enQuene(char element) {
    rear = (rear+1)%MaxSize;
    CQ[rear] = element;
}

void CirQuene ::deQuene() {
    front = (front+1)%MaxSize;
    CQ[front]=NULL;
}

void CirQuene ::Print() {
    for(int i=0;i<MaxSize;i++){
        if(CQ[i]!=NULL)
            cout<<CQ[i]<<" ";
    }
    cout<<endl;
}

bool CirQuene ::Judge() {
    if(front==rear){
        return 1;
    } else{
        return 0;
    }

    /*if((rear+1)%MaxSize == front){
        return 0;
    }*/
}