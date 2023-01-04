//
// Created by 杨业卿 on 2020/11/5.
//

#include <iostream>
#include <string>
#include <cmath>
using namespace std;



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
    void PrintChild(Noden* key);
    void Find(char ch,Noden*p);
    int GetNodeNum(Noden*p);
    int GetH(Noden*p);


    void Print(){
        PrintTree(root);
        cout<<endl;
    }
    void Find();
    void Depth();
    void Destory();
    void GetNum();

private:
    Noden* root;
    int length;
    int MAXSIZE=50;
    int h=0;
    int num=0;

};

int main(){

    Tree tr;
    tr.CreateTree();
    tr.Print();
    tr.GetNum();


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

void Tree ::Find(char ch,Noden* p) {
    if(p!=NULL){
        Find(ch,p->lchild);
        if(ch==p->data){
            cout<<"---Found"<<endl;
            cout<<p->lchild->data<<" "<<p->rchild->data<<endl;
            return;
        }
        Find(ch,p->rchild);

    }
}

int Tree ::GetH(Noden* p) {
    if(p==NULL){
        return 0;
    }
    if(p->lchild==NULL && p->rchild==NULL){
        return 1;
    }

    int lDepth = GetH(p->lchild)+1;
    int rDepth = GetH(p->rchild)+1;

    return max(lDepth,rDepth);
}

int Tree ::GetNodeNum(Noden *p) {
    if(p==NULL){
        return 0;
    }
    return 1+GetNodeNum(p->lchild)+GetNodeNum(p->rchild);

}



void Tree ::Find() {
    char ch;
    Noden *p;
    cout<<"enter the data you want to find:";
    cin>>ch;
    Find(ch,root);


}

void Tree ::Depth() {
    cout<<"height:"<<GetH(root)<<endl;
}

void Tree ::Destory() {
    DestoryTree(root);
    cout<<"--Destory succeed";
}

void Tree ::GetNum() {
    cout<<"num:"<<GetNodeNum(root)<<endl;
}