//
// Created by 杨业卿 on 2020/11/30.
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
    int number=0;  //顶点编号
    string data="0";    //顶点信息
    string describtion; //简介
};

class Map{
public:

    Map(){
        n=e=0;
    };
    void Create();
    void Print();
    void Search();
    int LocateVex(int v);

    void Floyd();   //Floyd算法主要实现部分
    void MinDis();
private:
    int n,e;    //顶点数与边数
    int edges[MAX][MAX]={-1}; //邻接矩阵 若非0则存储权值信息
    Vertex *vexs;   //储存顶点信息

    int V[10][10];
    int path[10][10];

};


int main(){

    Map mp;
    mp.Create();
    //mp.Print();
    //mp.Search();
    mp.Floyd();
    mp.MinDis();

    return 0;
}



void Map::Create() {
    n=10;
    e=16;

    vexs = new Vertex[n];

    vexs[0].data="学综楼";
    vexs[0].describtion = "学生综合服务楼 学生活动中心";

    vexs[1].data="美食园";
    vexs[1].describtion = "好吃的食堂";

    vexs[2].data="宿舍1";
    vexs[2].describtion = "宿舍楼";

    vexs[3].data="宿舍2";
    vexs[3].describtion = "宿舍楼";

    vexs[4].data="4教";
    vexs[4].describtion = "大型教学楼 有很多阶梯教室";

    vexs[5].data="礼堂";
    vexs[5].describtion = "举办大型活动的地点";

    vexs[6].data="图书馆";
    vexs[6].describtion = "逸夫图书馆";

    vexs[7].data="3教";
    vexs[7].describtion = "学生集中上课教学楼";

    vexs[8].data="软件楼";
    vexs[8].describtion = "软件工程专业楼";

    vexs[9].data="信息楼";
    vexs[9].describtion = "信息学部专用楼";

    for(int i=0;i<n;i++){           //初始化邻接数组
        for(int j=0;j<n;j++){
            edges[i][j]=0;
        }
    }


    for(int i=0;i<n;i++){
        vexs[i].number = i;
    }

    edges[0][1] = edges[1][0] = 25;
    edges[0][3] = edges[3][0] = 11;
    edges[0][5] = edges[5][0] = 25;
    edges[3][5] = edges[5][3] = 15;
    edges[1][9] = edges[9][1] = 35;
    edges[5][9] = edges[9][5] = 25;
    edges[9][6] = edges[6][9] = 10;
    edges[5][6] = edges[6][5] = 15;
    edges[5][2] = edges[2][5] = 35;
    edges[2][7] = edges[7][2] = 15;
    edges[6][7] = edges[7][6] = 10;
    edges[9][4] = edges[4][9] = 35;
    edges[6][4] = edges[4][6] = 25;
    edges[7][4] = edges[4][7] = 25;
    edges[4][8] = edges[8][4] = 12;
    edges[7][8] = edges[8][7] = 25;


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

void Map ::Search() {
    cout<<"学综楼"<<endl;
    cout<<"美食园"<<endl;
    cout<<"宿舍1"<<endl;
    cout<<"宿舍2"<<endl;
    cout<<"4教"<<endl;
    cout<<"礼堂"<<endl;
    cout<<"图书馆"<<endl;
    cout<<"3教"<<endl;
    cout<<"软件楼"<<endl;
    cout<<"信息楼"<<endl;
    cout<<"input the place you want to search:";

    string input;
    cin>>input;

    for(int i=0;i<n;i++){
        if(input==vexs[i].data){
            cout<<vexs[i].data<<endl;
            cout<<"describtion:"<<vexs[i].describtion<<endl;
            return;
        }
    }


}

void Map ::Floyd() {
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(i!=j && edges[i][j]==0){
                edges[i][j] = INFINTE;      //将图中未连通的点置为无穷大
            }
        }
    }


    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            V[i][j] = edges[i][j];


            if(i!=j && edges[i][j]<INFINTE){
                path[i][j] = i;
            } else{
                path[i][j] = -1;
            }

        }
    }


    for(int k=0;k<n;k++){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(V[i][j]>V[i][k]+V[k][j]){
                    V[i][j] = V[i][k]+V[k][j];
                    path[i][j] = path[k][j];
                }
            }
        }
    }

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cout<<V[i][j]<<" ";
        }
        cout<<endl;
    }
    cout<<"-----------------"<<endl;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cout<<path[i][j]<<" ";
        }
        cout<<endl;
    }

}

void Map ::MinDis() {   //查询并显示任意两点间最短路径
    cout<<"intput the TWO places you want to search:";
    string a="-1",b="-1";   //输入的两点名称

    std::vector<string>minpath; //使用vector数组存放最短路径

    int da,db;  //所返回的两点数字下标
    cin>>a>>b;

    for(int i=0;i<n;i++){   //查找并返回任意两点的数字下标
        if(a==vexs[i].data){
            da = i;
        }
        if(b==vexs[i].data){
            db = i;
        }
        if(a==b && a!="-1"){
            break;
        }
    }

    if(path[da][db]==da){   //若两点间没有其他点构成的最短路径 则直接返回两点 构成最短路径
        cout<<vexs[da].data<<"->"<<vexs[db].data<<endl;
        return;
    }


    minpath.push_back(vexs[db].data);

    int k=da;
    int temp=db;
    while (path[da][k]!=da){    //逐级遍历 将最短路径存入vector数组
        temp = path[da][temp];
        k = temp;
        minpath.push_back(vexs[k].data);
    }
    minpath.push_back(vexs[da].data);

    vector<string>::iterator it;    //使用迭代器进行遍历
    for(it=minpath.end();it!=minpath.begin();it--){
        cout<<*it<<"->";
    }
    cout<<vexs[db].data<<endl;
}