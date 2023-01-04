//
// Created by 杨业卿 on 2020/11/12.
//


#include <iostream>
#include <string>
#include <cmath>
using namespace std;



class Tree;
class Noden{        //树每个节点
public:
    friend class Tree;
private:
    Noden* lchild=NULL; //左子树
    Noden* rchild=NULL; //右子树
    int ltag=0,rtag=0;
    char data='Z';  //数据

};
class Tree{
public:
    Tree(); //构造函数
    void CreateTree();  //创建树
    void DestoryTree(Noden *&b);    //销毁树
    void PrintTree(Noden *p);   //递归打印树
    void Thread(Noden *&p);
    void PringOrder(Noden *t);
    void TP();


    void Print(){
        PrintTree(R->lchild);
        cout<<endl;
    }
    void Find();
    void Depth();
    void Destory();

private:
    Noden* R = new Noden;
    Noden* root;
    Noden* pre = new Noden;
    int length;
    int MAXSIZE=50;
    int h=0;



};

int main(){

    Tree tr;
    tr.CreateTree();
    tr.Print();
    cout<<endl;
    tr.TP();

    return 0;
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


    R->lchild = root;
}

void Tree ::PrintTree(Noden *p) {
    if(p==NULL){
        return;
    } else{
        PrintTree(p->lchild);   //遍历左孩子
        cout<<p->data;  //输出当前节点
        PrintTree(p->rchild);   //遍历右孩子
    }
}

void Tree ::Thread(Noden *&p) {

    if(p!=NULL){
        Thread(p->lchild);

        if(p->lchild==NULL){
            p->lchild = pre;
            p->ltag = 1;
        } else{
            p->ltag = 0;
        }

        if(pre->rchild == NULL){
            pre->rchild = p;
            pre->rtag = 1;
        } else{
            pre->rtag = 0;
        }

        pre = p;

        Thread(p->rchild);
        pre->lchild = root;
    }
    
}

void Tree ::PringOrder(Noden *t) {
    Noden* p = t->lchild;
    while (p!=t){
        if(p==NULL){
            return;
        }
        while (p->ltag==0){
            p=p->lchild;
            cout<<p->data;
        }
        while (p->rtag==1 && p->rchild!=t){
            p=p->rchild;
            cout<<p->data;
        }
        p = p->rchild;
    }
}

void Tree ::TP() {
    Thread(R->lchild);
    PringOrder(R->lchild);
    //cout<<"GI";
}