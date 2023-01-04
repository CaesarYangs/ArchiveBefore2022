//
// Created by 杨业卿 on 2020/10/14.
//


#include <iostream>
#include <string>

using namespace std;

class Symmetric{
public:
    Symmetric(){
        MAXSIZE=10;
        top=-1;
    };
    void Set();
    void Push(char element='0');
    char Pop();
    bool Solve();
    void ShowOutFromStack();


private:
    int MAXSIZE;
    char *array;
    int top;
    char *character;


};


int main(){

    Symmetric s;
    s.Set();
    cout<<s.Solve()<<endl;


    return 0;
}


void Symmetric ::Set() {
    cout<<"Enter the number of character:";
    cin>>MAXSIZE;
    character = new char[MAXSIZE];

    cout<<"Enter character:";

    for(int i=0;i<MAXSIZE;i++){
        cin>>character[i];
    }

    array = new char[MAXSIZE];

    if(MAXSIZE%2==0){
        for(int i=0;i<MAXSIZE/2;i++){
            Push(character[i]);
        }
    } else{
        for(int i=0;i<MAXSIZE/2;i++){
            Push(character[i]);
        }
    }


    /*cout<<endl;
    for(int i=0;i<MAXSIZE;i++){
        cout<<character[i];
    }
    cout<<endl;*/



}

void Symmetric ::Push(char element) {
    if(top!=MAXSIZE-1)
        ++top;
        array[top] = element;

}

char Symmetric ::Pop() {
    if(top==-1){
        return 'x';
    }
    int i = top;
    --top;
    return array[i];
}


void Symmetric ::ShowOutFromStack() {
    if(top==-1){
        cout<<"***Empty Stack***"<<endl;
        return;
    }
    cout<<"--------"<<endl;
    for(int i=top;i>=0;i--){
        cout<<array[i]<<endl;
    }
    cout<<"--------"<<endl;
}

bool Symmetric ::Solve() {

    if(MAXSIZE%2!=0){
        for(int i=MAXSIZE/2+1;i<MAXSIZE;i++){
            if(character[i]!=Pop()){
                return false;
            }
        }
    } else{
        for(int i=MAXSIZE/2;i<MAXSIZE;i++){
            if(character[i]!=Pop()){
                return false;
            }
        }
    }

    return true;
}