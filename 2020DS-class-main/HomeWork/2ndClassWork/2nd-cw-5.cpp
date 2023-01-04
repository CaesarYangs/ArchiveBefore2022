//
// Created by 杨业卿 on 2020/10/19.
//


#include <iostream>
#include <string>

using namespace std;

int jop(char element){
    if(element=='+'|| element=='-'){
        return 1;
    }

    if(element=='*'||element=='/'){
        return 2;
    }

    if(element=='('||element==')'){
        return 3;
    }else{
        return 0;
    }
}

class Node{
public:
    friend class ChainStack;  //使用友元类的方式组合数据类和操作类
private:
    char data;  //节点数据域
    Node* next=NULL;    //节点指针域
};

class ChainStack{
public:
    ChainStack();
    void List();
    void Push(char element='0');
    void Pop(char &ch);
    void ShowOutFromStack();
    bool JudgeEmpty();
    bool JudgeFull();
    char Top(){
        if(JudgeEmpty()){
            return 0;
        } else{
            return head->data;
        }
    }
    int rc(){
        return count;
    }

private:
    Node* head;
    int Maxlength;
    int count=0;
    Node* top=NULL;
};


class op{
public:
    op(){
        cout<<"enter the line:";
        cin>>input;
        length = input.length();
        strcpy(array, input.c_str());
    }
    void solve();


private:
    int length;
    char array[30]={'0'};
    string input;
    ChainStack optr,nums;
};

int main(){

    op o;
    o.solve();
    /*char ch;
    ChainStack a;
    a.Push('+');
    a.Pop(ch);

    cout<<ch;*/


    return 0;
}


ChainStack ::ChainStack() {
    head=NULL;
    Maxlength = 10;
}   //链式栈函数实现

void ChainStack ::List() {
    Node* p = new Node;
    p->data = '1';
    head = p;
    //delete p;
}

void ChainStack ::Push(char element) {

        /*do{
            cin>>enter;
            if(enter=='0'){
                return;
            }*/
            Node* ptr = new Node;
            ptr->data = element;
            ptr->next = head;
            head = ptr;
            top=head;
            //delete ptr;
            count++;



}   //压入栈

void ChainStack ::ShowOutFromStack() {
    Node* ptr = head;
    cout<<"--------"<<endl;
    for(int i=0;i<count;i++){
        cout<<ptr->data<<endl;
        ptr = ptr->next;
    }
    cout<<"--------"<<endl;
}

void ChainStack ::Pop(char &ch) {
    ch = top->data;
    if(count==1){

        delete top;
        top=NULL;
    }
    head = head->next;
    count--;
    delete top;
    top = head;



}   //弹出栈

bool ChainStack ::JudgeFull() {
    if(count==Maxlength){
        return 1;
    }else{
        return 0;
    }
}

bool ChainStack ::JudgeEmpty() {
    if(top==NULL){
        return 1;
    } else{
        return 0;
    }
}


void op::solve() {
    char ch;
    int count=0;
    int sidemark=0;
    for(int i=0;i<length;i++){
        if(array[i]>='0' && array[i]<='9'){
            cout<<array[i]<<"#";
        } else{
            if(jop(array[i])==3){
                if(array[i]==')'){
                    while (optr.Top()!='('){
                        optr.Pop(ch);
                        if(ch!='('&&ch!=')')
                            cout<<ch;
                    }
                    optr.Pop(ch);
                    sidemark=0;
                    continue;
                }
                sidemark=1;
                optr.Push(array[i]);
                continue;
            }
            if(jop(array[i])>jop(optr.Top())||optr.JudgeEmpty()==1||sidemark==1){
                optr.Push(array[i]);
                //count++;
            } else if(jop(array[i])<jop(optr.Top())){
                optr.Pop(ch);
                if(ch!='('&&ch!=')')
                    cout<<ch;
            } else{
                optr.Pop(ch);
                if(ch!='('&&ch!=')')
                    cout<<ch;
            }
        }

    }

    while (optr.JudgeEmpty()==0){

        optr.Pop(ch);
        if(ch!='(' && ch!=')')
            cout<<ch;
    }

}   //解决表达式转换问题