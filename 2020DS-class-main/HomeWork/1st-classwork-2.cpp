//
// Created by 杨业卿 on 2020/9/22.
//

#include <iostream>

using namespace std;
class List;

class Node{
public:
    friend class List;  //使用友元类的方式组合数据类和操作类
private:
    char data='n';  //节点数据域
    Node* next=NULL;    //节点指针域

};

class List{
public:
    List(){
        head = NULL;
        length = 0;
    }   //构造函数
    void SetList();     //创建链表
    void PrintList();   //打印链表
    void Insert(char value,int pos);    //插入数据
    void Delete(int pos);   //删除数据
    Node* FindP(int pos);   //按序号查找
    void FindV(char value); //按值查找
    int Count();    //返回链表长
    void SingleOutput(Node *p); //单个输出

private:
    Node* head; //头指针
    int length; //链表长

};



int main(){

    List list;
    list.SetList();
    int marker,pos;
    char value;


    do{
        cout<<"*****************************"<<endl;    //do循环负责用户界面
        cout<<"1.显示链表内容"<<endl;
        cout<<"2.插入元素"<<endl;
        cout<<"3.删除元素"<<endl;
        cout<<"4.获得当前链表长度"<<endl;
        cout<<"5.按序号查找"<<endl;
        cout<<"6.按值查找"<<endl;
        cout<<"0.Exit"<<endl;
        cout<<endl;

        int n=0;
        cin>>marker;
        switch (marker) {
            case 1:
                list.PrintList();
                break;

            case 2:
                cout<<"输入插入的位置:";
                cin>>pos;
                cout<<"输入插入的值:";
                cin>>value;
                list.Insert(value,pos);
                list.PrintList();
                break;

            case 3:
                cout<<"输入删除的位置:";
                cin>>pos;
                list.Delete(pos);
                list.PrintList();
                break;

            case 4:
                cout<<"长度："<<list.Count()<<endl;
                break;

            case 5:
                cout<<"输入要查找的序号:";
                cin>>pos;
                list.SingleOutput(list.FindP(pos));
                break;

            case 6:
                cout<<"输入要查找的值:";
                cin>>value;
                list.FindV(value);

                break;

        }

    }while (marker != 0);


    return 0;
}

void List :: SetList() {
    char datachar = 'a';
    Node *p = new Node;
    head = p;
    p->data = '0';
    Node *pre = head;

    for (int i=0;i<10;i++){
        p = new Node;
        p->data = datachar;
        pre->next = p;
        pre = p;
        ++length;
        ++datachar;

    }

}

void List :: PrintList() {
    Node* p=head->next;
    cout<<"***";
    /*for(int i=0;i<length;i++){
        cout<<p->data<<" ";
        p = p->next;
    }*/

    while (p!=NULL){
        cout<<p->data<<" ";
        p = p->next;
    }
    cout<<"***"<<endl;
}

void List :: Insert(char value,int pos) {
    if (pos > length){
        cout<<"下标越界"<<endl;
        return;
    }
    Node* insp = new Node;
    Node* p = FindP(pos);

    insp->data = value;
    if(p->next==NULL){
        insp->next = NULL;
        p->next = insp;
    }else{
        insp->next = p->next;
        p->next = insp;
    }

    length++;


}

void List :: Delete(int pos) {
    Node* p = FindP(pos-1);
    p->next = p->next->next;
    //delete p;
}

Node* List ::  FindP(int pos){
    Node* p = head;
    length = Count();

    for(int i=-1;i<length;i++){
        if (i+1 == pos){
            return p;

        } else{
            p = p->next;
        }
    }


}

int List :: Count() {
    length = 0;
    Node* p = head->next;
    do{
        p = p->next;
        length++;
    }while(p!=NULL);
    return length;

}

void List :: FindV(char value) {
    Node* p = head->next;
    int n=1;
    do{
        if(p->data==value){
            cout<<"已找到"<<value<<" 第"<<n<<"个"<<endl;
            break;
        } else{
            n++;
            p = p->next;
        }
        if(p==NULL){
            cout<<"未找到"<<endl;
        }
    }while (p!=NULL);


}

void List ::SingleOutput(Node *p) {
    cout<<p->data<<endl;
}