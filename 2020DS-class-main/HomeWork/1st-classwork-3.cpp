//
// Created by 杨业卿 on 2020/9/24.
//


#include <iostream>

using namespace std;
class JosephusCircle;

class Node{
public:
    friend class JosephusCircle;
private:
    int data;
    Node* next=NULL;

};  //节点类

class JosephusCircle{
public:
    JosephusCircle(){
        tail=NULL;
        n=1;
    }   //构造函数
    void SetCircle();   //初始化循环链表
    void PrintCircle(); //输出循环链表
    void Josephus();    //解决约瑟夫问题的主函数
    void Count();



private:
    Node* tail; //尾节点
    Node* head;
    int n,s,m;
    int length=0;

};  //约瑟夫类



int main(){

    JosephusCircle cir;
    cir.SetCircle();
    cir.PrintCircle();
    cir.Josephus();
    cir.PrintCircle();

    return 0;
}


void JosephusCircle :: SetCircle() {
    Node* pre;
    cout<<"n:";
    cin>>n;
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
        Node* p = new Node;
        p->data = i+1;
        if(i==0){
            head = p;
            pre = p;
        } else{
            pre->next = p;
            pre = p;
        }

        if(i==n-1){
            tail = p;
            tail->next = head;

        }

    }
    length = n;
}

void JosephusCircle :: PrintCircle() {
    Node* p = tail->next;
    cout<<"***";
    /*do{
        cout<<p->data<<" ";
        p = p->next;
    }while (p!=tail->next);*/

    for (int i=0;i<length;i++){
        cout<<p->data<<" ";
        p = p->next;
    }
    cout<<"***"<<endl;
}

void JosephusCircle :: Josephus() {
    Node* p = tail->next;
    Node* pre=NULL,*del=NULL;
    int num = 0;
    for(int i=0;i<s-1;i++){
        p=p->next;
    }

    for(int i=0;i<n-1;i++){
        for(int j=0;j<m-1;j++){
            pre = p;
            p = p->next;
        }
        del = pre->next;
        p=p->next;
        cout<<"出圈:"<<del->data<<endl;
        pre->next = del->next;
        tail = del->next;
        ++num;

        if (num == n-1){
            break;
        }
    }
    length = 1;
    delete p;
}

void JosephusCircle ::Count() {

}