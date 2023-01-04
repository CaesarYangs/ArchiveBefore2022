//
// Created by 杨业卿 on 2020/11/2.
// tree1-1


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
    char data='Z';  //数据

};
class Tree{
public:
    Tree(); //构造函数
    void CreateTree();  //创建树
    void DestoryTree(Noden *&b);    //销毁树
    void PrintTree(Noden *p);   //递归打印树
    void PrintChild(Noden* key);    //打印孩子节点
    void Find(char ch,Noden*p); //查找输入节点
    int GetH(Noden*p);  //返回树高


    void Print(){
        PrintTree(root);
        cout<<endl;
    }
    void Find();
    void Depth();
    void Destory();

private:
    Noden* root;
    int length;
    int MAXSIZE=50;
    int h=0;


};

int main(){

    Tree tr;
    tr.CreateTree();
    tr.Print();

    tr.Find();
    tr.Depth();
    tr.Destory();



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
        return; //如果为叶子节点就停止遍历
    } else{
        DestoryTree(b->lchild); //释放左孩子
        DestoryTree(b->rchild); //释放又孩子
        delete b;
    }
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

void Tree ::Find(char ch,Noden* p) {
    if(p!=NULL){
        Find(ch,p->lchild); //遍历查找所有左孩子
        if(ch==p->data){
            cout<<"---Found"<<endl;
            cout<<p->lchild->data<<" "<<p->rchild->data<<endl;  //打印该节点的左孩子与右孩子节点值
            return;
        }
        Find(ch,p->rchild); //遍历查找所有右孩子

    }
}

int Tree ::GetH(Noden* p) {
   if(p==NULL){
       return 0;    //若当前节点为空节点停止向下遍历
   }
   if(p->lchild==NULL && p->rchild==NULL){
       return 1;    //若当前节点为叶子结点则返回1 开始第一层遍历
   }

   int lDepth = GetH(p->lchild)+1;  //进行遍历 计算左子树高度
   int rDepth = GetH(p->rchild)+1;  //进行遍历 计算右子树高度

    return max(lDepth,rDepth);  //比较二者高度 将较大值输出
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