//
// Created by 杨业卿 on 2020/11/21.
//

#include <iostream>
#include<stack>
#include<queue>

const int MAXNUM=10;
using namespace std;

class Map;

class Node{
public:
    friend class Map;
private:
    int data;   //顶点数据
    Node* firstarc; //

    int adjvex; //该边的终点编号
    Node* nextarc;  //指向下一条边的指针
    int weight=0; //边权重信息

};

class Map{
public:
    Map(){
        n=e=0;
    };
    void CreatUGD();
    int LocateVex(int v);
    void Display();

    int *Visited;
    void DFSTraverse();
    void DFS(int v);    //递归算法
    void DFS(); //非递归算法

    void BFS(int v);    //广度优先搜索遍历算法 v为开始遍历的节点

private:
    int n,e; //顶点数n 边数e
    Node *adjlist;  //动态数组声明
};


int main(){

    Map mp;
    mp.CreatUGD();
    mp.Display();

    //mp.DFSTraverse();
    mp.DFS();
    cout<<endl;
    mp.BFS(0);
    return 0;
}

void Map ::CreatUGD() {
    cout<<"enter the num of the dot and line:"<<endl;
    cout<<"n:";
    cin>>n;
    cout<<"e:";
    cin>>e;

    adjlist = new Node[n];
    cout<<"enter the dot info one by one"<<endl;   //逐个输入顶点信息
    for(int i=0;i<n;i++){
        cin>>adjlist[i].data;
        adjlist[i].firstarc=NULL;   //将数组节点指针域置空
        adjlist[i].nextarc=NULL;    //将后续节点指针域置空
    }

    int v1,v2;
    int k,l;
    int w=0;

    for(int i=0;i<e;i++){   //输入各边信息 构造临接表
        cout<<"enter the relationship of the two dots"<<endl;
        cin>>v1>>v2;
        /*cout<<"weight:";
        cin>>w;*/
        k=LocateVex(v1);
        l=LocateVex(v2);

        Node* p1 = new Node;
        p1->adjvex = l;
        p1->weight = w;
        p1->nextarc = adjlist[k].firstarc;
        adjlist[k].firstarc = p1;

        Node* p2 = new Node;
        p2->adjvex = k;
        p2->weight = w;
        p2->nextarc = adjlist[l].firstarc;
        adjlist[l].firstarc = p2;

    }


}

int Map ::LocateVex(int v) {
    for(int i=0;i<n;i++){
        if(v==adjlist[i].data){
            return i;
        }
    }
    return -1;
}

void Map::Display() {
    Node* p;
    for(int i=0;i<n;i++){
        p=adjlist[i].firstarc;
        cout<<i<<" ";
        cout<<"|"<<adjlist[i].data<<"|"<<" ";
        while (p!=NULL){
            cout<<"->"<<p->adjvex<<" "<<p->weight;
            p=p->nextarc;
        }
        cout<<"^"<<endl;

    }
}

void Map ::DFSTraverse() {
    Visited = new int[n];
    for(int i=0;i<n;i++){
        Visited[i]=0;   //初始化访问标志数组
    }

    /*for(int i=0;i<n;i++){
        if(Visited[i]==0){

        }
    }*/
    DFS(0);
}   //深度优先搜索 递归算法

void Map::DFS(int v) {
    cout<<adjlist[v].data<<" ";
    Visited[v]=1;
    Node* p = adjlist[v].firstarc;
    int w;
    while (p!=NULL){
        w = p->adjvex;
        if(Visited[w]==0){
            DFS(w);
        }
        p=p->nextarc;
    }
}   //深度优先搜索 遍历算法主要实现部分

void Map ::DFS() {
    cout<<"DFS:";
    stack<int> s;
    int v=0;
    int ps,marker=0;
    int out,next;

    Visited = new int[n];
    for(int i=0;i<n;i++){
        Visited[i]=0;   //初始化访问标志数组
    }

    Node* p = adjlist[v].firstarc;
    s.push(v);

    while (!s.empty()){
        v = s.top();
        s.pop();

        if(Visited[v]==0){
            cout<<adjlist[v].data<<" ";
            Visited[v]=1;
            //s.push(v);
        }

        p = adjlist[v].firstarc;
        while (p!=NULL){
            if(Visited[p->adjvex]==0){
                s.push(p->adjvex);
            }
            p = p->nextarc;
        }


    }


}   //深度优先搜索 非递归算法+主要实现部分

void Map ::BFS(int v) {
    cout<<"BFS:";
    int w;
    Node* p=new Node;
    queue<int>qu;   //使用stl的队列容器


    Visited = new int[n];
    for(int i=0;i<n;i++){
        Visited[i]=0;
    }
    cout<<adjlist[v].data<<" ";
    Visited[v]=1;   //访问标志数组置空
    qu.push(v);

    while (!qu.empty()){
        w = qu.front();
        p = adjlist[w].firstarc;
        while (p!=NULL){
            if(Visited[p->adjvex]==0){
                cout<<adjlist[p->adjvex].data<<" ";
                Visited[p->adjvex]=1;
                qu.push(p->adjvex);
            }
            p = p->nextarc;
        }
        qu.pop();
    }
    cout<<endl;
}       //广度优先遍历方法主要实现部分






