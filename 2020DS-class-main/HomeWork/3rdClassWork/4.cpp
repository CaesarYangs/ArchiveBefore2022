//
// Created by 杨业卿 on 2020/11/5.
// tree1-4 H


#include <iostream>
#include <string>
#include <cmath>
using namespace std;
class HTree;

class HTreeNode{
public:
    friend class HTree;
private:
    char data=0;
    int Nweight;
    int parent,lchild,rchild;

};


class HTree{
public:
    HTree();    //构造函数
    void input();   //输入函数 用于用户输入一个字符串
    void countWeight(); //计算权重函数 用于将用户输入的字符串转换为字符数组并计算每个字母的权重
    void CreateHTree(); //哈夫曼树构造函数 用于根据句子内的权重构造哈夫曼树
    void Select(int pos,int &s1,int &s2);   //选择函数 用于构造哈夫曼树的操作 主要作用：在给定范围内选出两个最小权重点的下标
    void PrintHTree();  //打印哈夫曼树 包括字符 权重 双亲节点 以及左右孩子节点

    void Encode();  //主编码函数
    void Decode();  //主解码函数 包含用户界面以及字符串输入处理
    void Decode(string input);  //解码函数 主要用于解码算法实现


private:
    int a_length;
    char array[50];
    char alpabet[26];
    int weight[26]={0};
    int totalnum=0;
    int leaf=0;
    HTreeNode *HN;


    //code//
    char *HCode;

};


int main(){

    HTree htr;
    htr.input();
    htr.countWeight();
    htr.CreateHTree();
    htr.PrintHTree();
    htr.Encode();
    htr.Decode();


    return 0;
}

void HTree ::input() {
    string input;
    cout<<"enter the sentence:"<<endl;
    //cin>>input;
    getline(cin,input);

    a_length = input.length();
    strcpy(array,input.c_str());

    /*for(int i=0;i<a_length;i++){
        cout<<array[i];
    }
    cout<<endl;*/
}

void HTree ::countWeight() {

    for(int i=0;i<26;i++){
        for(int j=0;j<a_length;j++){
            /*if(array[j]==' '){
                continue;
            }*/
            if(alpabet[i]==array[j]){
                weight[i]++;
            }
        }
    }

    char a='a';
    for(int i=0;i<26;i++){
        cout<<a<<" ";
        a++;
    }
    cout<<endl;
    for(int i=0;i<26;i++){
        if(weight[i]!=0){
            totalnum++;
        }
        cout<<weight[i]<<" ";
    }
    cout<<endl;

    totalnum=totalnum*2-1;
    //cout<<totalnum;

}

HTree ::HTree() {
    int a='a';
    for(int i=0;i<26;i++){
        alpabet[i]=a;
        a++;
    }
}

void HTree ::Select(int pos, int &s1, int &s2) {
    int min1=20,min2=20;
    for(int i=1;i<=pos;i++){
        if(HN[i].parent==0){
            if(HN[i].Nweight<min1){
                min1=HN[i].Nweight;
                s1=i;
            }
        }
    }

    for(int i=1;i<=pos;i++){
        if(i==s1){
            continue;
        }
        if(HN[i].parent==0){
            if(HN[i].Nweight<=min2){
                min2=HN[i].Nweight;
                s2=i;
            }
        }
    }
}

void HTree ::CreateHTree() {
    int s1=-1,s2=-1;
    leaf = (totalnum+1)/2;
    int TWeight[leaf];
    HN = new HTreeNode[totalnum+1];
    for(int i=0;i<=totalnum;i++){
        HN[i].parent=0;
        HN[i].lchild=0;
        HN[i].rchild=0;
    }

    int n=1;
    char a='a';
    for(int i=0;i<26;i++){
        if(weight[i]!=0){
            HN[n].Nweight=weight[i];
            HN[n].data=a+i;
            n++;
        }
    }

    /*for(int i=0;i<leaf;i++){
        cout<<HN[i].Nweight<<" ";
    }
    cout<<endl;*/
    for(int i=leaf+1;i<=totalnum;i++){
        Select(i-1,s1,s2);
        HN[s1].parent=i;
        HN[s2].parent=i;

        HN[i].lchild=s1;
        HN[i].rchild=s2;
        HN[i].Nweight=HN[s1].Nweight+HN[s2].Nweight;
    }

    //cout<<s1<<" "<<s2<<endl;


}

void HTree::PrintHTree() {
    for(int i=1;i<totalnum+1;i++){
        cout<<HN[i].data<<" ";
        if(HN[i].data==0){
            cout<<" ";
        }
        cout<<HN[i].Nweight<<" ";
        cout<<HN[i].parent<<" ";
        cout<<HN[i].lchild<<" ";
        cout<<HN[i].rchild<<" ";
        cout<<endl;
    }
    cout<<endl;

}

void HTree ::Encode() {

    string Hcode;
    int num=10;
    char term[num];
    int start = num-1;
    term[start]='/0';
    int f,c;


    for(int i=0;i<a_length;i++){
        start=num-1;
        for(int j=1;j<=leaf;j++){
            if(HN[j].data==array[i]){
                c=j;
                f=HN[j].parent;
                while (f!=0){
                    --start;
                    if(HN[f].lchild==c){
                        term[start]='0';
                    } else{
                        term[start]='1';
                    }
                    c=f;
                    f=HN[f].parent;
                }
                //strcpy(Hcode.c_str(),);
                cout<<array[i]<<":";
                for(int n=start;n<num-1;n++){
                    cout<<term[n];
                }
                cout<<" ";
                break;
            }
        }
    }

    cout<<endl;


}

void HTree ::Decode(string input) {

    string message;

    char codearray[50];
    int c_length = input.length();
    strcpy(codearray, input.c_str());

    /*for(int i=0;i<c_length;i++){
        cout<<codearray[i];
    }*/

    int c, f;

    int j = totalnum;


    for (int i = 0; i < c_length; i++) {
        if (codearray[i] == '0') {
            j = HN[j].lchild;
        } else {
            j = HN[j].rchild;
        }

        if (HN[j].lchild == 0 || HN[j].rchild == 0) {
            // << HN[j].data;
            message.push_back(HN[j].data);
            //return HN[j].data;
            j=totalnum;
        }

    }

    cout<<"Decoded!"<<endl<<"message:"<<message<<endl;



}

void HTree ::Decode() {

    string message;
    string input;
    /*do{
        cin>>input;
        if(input=="exit"){
            break;
        }
        message.push_back(Decode(input));
    }while (input!="exit");*/

    cin>>input;
    Decode(input);

    //cout<<"Decoded!"<<endl<<"message:"<<message<<endl;


}

