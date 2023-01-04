//
// Created by 杨业卿 on 2020/11/5.
// tree1-4 H


#include <iostream>
#include <string>
#include <cmath>
using namespace std;


class HTree{
public:
    HTree();
    void input();
    void countWeight();

private:

    int a_length;
    char array[50];
    char alpabet[26];
    int weight[26]={0};

};


int main(){

    HTree htr;
    htr.input();
    htr.countWeight();



    return 0;
}

void HTree ::input() {
    string input;
    cout<<"enter the sentence:";
    cin>>input;

    a_length = input.length();
    strcpy(array,input.c_str());

    /*for(int i=0;i<a_length;i++){
        cout<<array[i];
    }*/

}

void HTree ::countWeight() {

    for(int i=0;i<26;i++){
        for(int j=0;j<a_length;j++){
            if(alpabet[i]==array[j]){
                weight[i]++;
            }
        }
    }

    for(int i=0;i<26;i++){
        cout<<"";
    }

}

HTree ::HTree() {
    int a='a';
    for(int i=0;i<26;i++){
        alpabet[i]=a;
        a++;
    }
}