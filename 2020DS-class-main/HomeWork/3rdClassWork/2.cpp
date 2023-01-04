//
// Created by 杨业卿 on 2020/11/2.
//


#include <iostream>
#include <string>
#include <cmath>
using namespace std;

class Noden;
class SeqStack{
public:
    SeqStack();
    void Push(Noden* element);
    Noden* Pop(Noden* p);
    bool JudgeEmpty();
    bool JudgeFull();
    void AllOutFromStack();

private:
    int MAXSIZE=50;
    Noden *array[50];
    int top;


};

class Tree;
class Noden{
public:
    friend class Tree;
private:
    Noden* lchild=NULL;
    Noden* rchild=NULL;
    char data='Z';

};
class Tree{
public:
    Tree();
    void CreateTree();
    void DestoryTree(Noden *&b);
    void PrintTree(Noden *p);
    void PrintTreeII();



    void Print();


private:
    Noden* root;
    int length;
    int MAXSIZE=50;


};


int main(){

    Tree tr;
    tr.CreateTree();
    tr.Print();
    tr.PrintTreeII();

    return 0;
}


//顺序栈
SeqStack::SeqStack()
{
    top = -1;

}       //顺序栈函数实现

void SeqStack::Push(Noden* element) { //压入栈
    ++top;
    array[top] = element;

}

Noden* SeqStack::Pop(Noden*p) {  //弹出栈
    p = array[top];
    array[top] = NULL;
    top--;
    return p;
}

void SeqStack::AllOutFromStack() {
    if (top == -1) {
        cout << "***Empty Stack***" << endl;
        return;
    }
    cout << "--------" << endl;
    for (int i = top; i >= 0; i--) {
        cout << array[i] << endl;
    }
    cout << "--------" << endl;
}

bool SeqStack::JudgeEmpty() {   //判断栈空
    if (top == -1) {
        return 1;
    } else {
        return 0;
    }
}

bool SeqStack::JudgeFull() {    //判断栈满
    if (top == MAXSIZE - 1) {
        return 1;
    } else {
        return 0;
    }
}



Tree ::Tree() {
    root = NULL;
}

void Tree ::CreateTree() {
    Noden* Stack[MAXSIZE],*p;
    int top=-1,k,j=0;
    char ch;
    char array[50];
    string input;
    root = NULL;

    cout<<"enter the tree expression:";
    cin>>input;
    length = input.length();
    strcpy(array, input.c_str());

    ch=array[j];
    while (ch!='\0'){
        switch (ch) {
            case '(':
                top++;
                Stack[top] = p;
                k=1;
                break;

            case ')':
                top--;
                break;

            case ',':
                k=2;
                break;

            default:
                p = new Noden;
                p->data = ch;
                p->lchild = p->rchild = NULL;

                if(root == NULL){
                    root = p;
                } else{
                    switch (k) {
                        case 1:
                            Stack[top]->lchild = p;
                            break;

                        case 2:
                            Stack[top]->rchild = p;
                            break;
                    }
                }

                break;

        }
        j++;
        ch = array[j];
    }


}

void Tree ::DestoryTree(Noden *&b) {
    if(b==NULL){
        return;
    } else{
        DestoryTree(b->lchild);
        DestoryTree(b->rchild);
        delete b;
    }
}

void Tree ::PrintTree(Noden *p) {
    if(p==NULL){
        return;
    } else{
        PrintTree(p->lchild);
        cout<<p->data;
        PrintTree(p->rchild);
    }
}

void Tree ::PrintTreeII() {
    cout<<"非递归算法：";
    Noden* p=root;  //根节点

    SeqStack sq;
    while (sq.JudgeEmpty()==0 || p!=NULL){

        while (p!=NULL){    //不为空的时候
            sq.Push(p); //入栈
            p=p->lchild;    //继续向左遍历
        }

        if(sq.JudgeEmpty()==0){ //栈不为空时
            p = sq.Pop(p);  //弹出栈
            cout<<p->data;  //打印数据
            p=p->rchild;    //向右遍历

        }
    }
    cout<<endl;
}



void Tree ::Print() {
    cout<<"递归算法：";
    PrintTree(root);
    cout<<endl;
}   //递归算法



