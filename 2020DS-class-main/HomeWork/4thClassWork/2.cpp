//
// Created by 杨业卿 on 2020/11/26.
//


#include <iostream>
#include <algorithm>
#include<stack>
#include <vector>
#include<queue>
#define INFINTE 32767
#define MAX 15 //最大顶点个数
using namespace std;
class Map;

class Vertex{
public:
    friend class Map;
private:
    int number=-1;  //顶点编号
    int data=-1;    //顶点信息
};

class Map{
public:

    Map(){
        n=e=0;
    };
    void Create();
    void Print();
    int LocateVex(int v);

    void Prim(int v);   //Prim算法主要实现部分
    void Kruskal(int v);    //Kruskal算法主要实现部分
private:
    int n,e;    //顶点数与边数
    int edges[MAX][MAX]={-1}; //邻接矩阵 若非0则存储权值信息
    Vertex *vexs;   //储存顶点信息
};

struct E{   //用来实现克鲁斯卡尔算法的数组结构
    int estart=0; //起始顶点
    int eend=0;   //终止顶点
    int eweight=0;    //权值
};



int main(){

    Map mp;
    mp.Create();
    mp.Print();

    //mp.Prim(0);
    mp.Kruskal(0);

    return 0;
}

bool cmp(E a,E b){
    if(a.eweight<b.eweight){
        return 1;
    } else{
        return 0;
    }
}


void Map::Create() {
    cout<<"enter the num of the dot and line:"<<endl;
    cout<<"n:";
    cin>>n;
    cout<<"e:";
    cin>>e;

    vexs = new Vertex[n];

    cout<<"enter the dot info one by one"<<endl;   //逐个输入顶点信息
    for(int i=0;i<n;i++){
        cin>>vexs[i].data;
    }

    for(int i=0;i<n;i++){           //初始化邻接数组
        for(int j=0;j<n;j++){
            edges[i][j]=0;
        }
    }

    int v1,v2,w;

    for(int k=0;k<e;k++){
        int i,j;
        cout<<"enter the dot of the line and its weight:"<<endl;
        cout<<"v1:";
        cin>>v1;
        cout<<"v2:";
        cin>>v2;
        cout<<"weight:";
        cin>>w;

        i=LocateVex(v1);
        j=LocateVex(v2);
        edges[i][j] = w;    //添加权值信息
        edges[j][i] = edges[i][j];  //将同一边的两顶点赋值并存储
    }



    cout<<"---successfully done---"<<endl;


}

void Map::Print() {     //打印整个邻接矩阵表示的图
    for(int i=0;i<n;i++){
        cout<<vexs[i].data<<": ";
        for(int j=0;j<n;j++){
            cout<<edges[i][j]<<" ";
        }
        cout<<endl;
    }
    cout<<endl;
}

int Map ::LocateVex(int v) {
    for(int i=0;i<n;i++){
        if(v==vexs[i].data){
            return i;
        }
    }
    return -1;
}

void Map::Prim(int v) {

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(i!=j && edges[i][j]==0){
                edges[i][j] = INFINTE;      //将图中未连通的点置为无穷大
            }
        }
    }


    int mincost[n];
    int close[n];

    vector<int> tree;
    tree.push_back(vexs[v].data);   //利用vector数组存储最短生成树

    int min,k;


    for(int i=0;i<n;i++){
        mincost[i] = edges[v][i];   //初始化最小长度数组以及最近点集数组
        close[i] = v;
    }

    for(int i=1;i<n;i++){
        min = INFINTE;
        for(int j=0;j<n;j++){
            if(mincost[j]!=0 && mincost[j]<min){
                min = mincost[j];
                k = j;
            }
        }

        tree.push_back(vexs[k].data);
        mincost[k]=0;

        for(int j=0;j<n;j++){   //调整点的集合
            if(mincost[j]!=0 && edges[k][j]<mincost[j]){
                mincost[j] = edges[k][j];
                close[j] = k;
            }
        }
    }


    cout<<"Prim result:";
    for(int i=0;i<n;i++){   //打印prim算法生成的最短生成树
        cout<<tree[i]<<"->";
    }
    cout<<endl;

}

void Map ::Kruskal(int v) {
    E E[n];
    int set[n];
    int u1,v1,sn1,sn2,k=0;

    for(int i=0;i<n;i++){   //初始化二维数组 用于存储点与边的信息
        for(int j=i;j<n;j++){   //仅考虑一半的二维数组 避免重复
            if(edges[i][j]!=0){ //若邻接数组中不为0则两点之间有连接 则赋值
                E[k].estart = i;
                E[k].eend = j;
                E[k].eweight = edges[i][j];
                k++;
            }
        }
    }

    sort(E,E+n-1,cmp);  //将数组元素按照权值升序排列

    /*for(int i=0;i<e;i++){
        cout<<E[i].eweight<<" ";
    }*/

    for(int i=0;i<n;i++){
        set[i] = i;
    }

    k=1;
    int j=0;
    while (k<n){
        u1 = E[j].estart;
        v1 = E[j].eend;

        sn1 = set[u1];  //赋值到标志数组 用于控制是否图之间连通
        sn2 = set[v1];

        cout<<"Kruskal result:"<<endl;
        if(sn1!=sn2){   //若不连通 则打印输出
            cout<<"u-v: "<<vexs[u1].data<<"-"<<vexs[v1].data<<" w:"<<E[j].eweight<<endl;
            k++;

            for(int i=0;i<n;i++){
                if(set[i]==sn2){
                    set[i]=sn1; //将两点置为相同集合 表示一个连通分量
                }
            }
        }

        j++;

    }

}



