//
// Created by 杨业卿 on 2020/9/14.
//

#include <iostream>

using namespace std;

int main(){

    int a=2,b=5;
    int array[10] = {1,2,3,4,5,6,7,8,9,0};
    int front,rear;

    for (int i=0;i<10;i++){         //遍历 求出要删除的前后数组下标位置
        if (array[i]==a){
            front = i;
            //cout<<front<<endl;
        }
        if (array[i] == b){
            rear = i;
            //cout<<rear<<endl;
            break;
        }
    }

    int num = (rear - front)/2+1;   //算一共要删的个数
    //cout<<num<<endl;


    for(int i=rear;i<10;i++){       //在数组上删除

        array[i-num] = array[i];

    }

    for(int i=0;i<10-num;i++){      //打印
        cout<<array[i]<<" ";
    }




    return 0;
}