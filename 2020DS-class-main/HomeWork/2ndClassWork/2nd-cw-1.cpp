//
// Created by 杨业卿 on 2020/10/12.
//

#include <iostream>
#include <string>

using namespace std;
class ChainStack;

class SeqStack{
public:
    SeqStack();
    void Push(char element='0');
    char Pop();
    bool JudgeEmpty();
    bool JudgeFull();
    void AllOutFromStack();

private:
    int MAXSIZE=10;
    char *array;
    int top;


};

class Node{
public:
    friend class ChainStack;  //使用友元类的方式组合数据类和操作类
private:
    char data='n';  //节点数据域
    Node* next=NULL;    //节点指针域
};

class ChainStack{
public:
    ChainStack();
    void List();
    void Push(char element='0');
    void Pop();
    void ShowOutFromStack();
    bool JudgeEmpty();
    bool JudgeFull();

private:
    Node* head;
    int Maxlength;
    int count=0;
    Node* top=NULL;
};

int main() {
    SeqStack seq;       //测试顺序栈数据
    seq.Push();
    seq.AllOutFromStack();
    cout<<"FULL?:"<<seq.JudgeFull()<<endl;
    seq.Pop();
    seq.Pop();
    seq.AllOutFromStack();
    cout<<"FULL?:"<<seq.JudgeFull()<<endl;



    ChainStack ch;          //测试链式栈数据
    ch.Push();
    ch.ShowOutFromStack();
    ch.Pop();
    ch.ShowOutFromStack();
    cout<<"empty?:"<<ch.JudgeEmpty()<<endl;


        return 0;
}
//顺序栈
    SeqStack::SeqStack()
    {
        array = new char[MAXSIZE];
        top = -1;

    }       //顺序栈函数实现

    void SeqStack::Push(char element) { //压入栈
        if (JudgeFull() == 1) {
            cout << "***Stack Full***" << endl;
            return;
        }
        if (element == '0') {
            char enter;
            cout << "Input to the stack:(enter 0 to end)" << endl;
            do {
                cin >> enter;
                if (enter != '0') {
                    ++top;
                } else {
                    return;
                }
                array[top] = enter;

            } while (enter != '0');
        } else {

        }
    }

    char SeqStack::Pop() {  //弹出栈
        char r = array[top];
        array[top] = NULL;
        top--;
        return r;
    }

    void SeqStack::AllOutFromStack() {
        if (top == -1) {
            cout << "***Empty Stack***" << endl;
            return;
        }
        cout << "--------" << endl;
        for (int i = top; i >= 0; i--) {
            cout << array[i] << endl;
        }
        cout << "--------" << endl;
    }

    bool SeqStack::JudgeEmpty() {   //判断栈空
        if (top == -1) {
            return 1;
        } else {
            return 0;
        }
    }

    bool SeqStack::JudgeFull() {    //判断栈满
        if (top == MAXSIZE - 1) {
            return 1;
        } else {
            return 0;
        }
    }

//--------------------------------------------------------------//
//链式栈
    ChainStack::ChainStack()
    {
        head = NULL;
        Maxlength = 10;
    }   //链式栈函数实现

    void ChainStack::List() {
        Node *p = new Node;
        p->data = '1';
        head = p;
        //delete p;
    }

    void ChainStack::Push(char element) {
        char enter;
        if (element == '0') {
            cout << "Input to the stack:(enter 0 to end)" << endl;
            do {
                cin >> enter;
                if (enter == '0') {
                    return;
                }
                Node *ptr = new Node;
                ptr->data = enter;
                ptr->next = head;
                head = ptr;
                top = head;
                //delete ptr;
                count++;

            } while (enter != '0');


        }
    }

    void ChainStack::ShowOutFromStack() {
        Node *ptr = head;
        cout << "--------" << endl;
        for (int i = 0; i < count; i++) {
            cout << ptr->data << endl;
            ptr = ptr->next;
        }
        cout << "--------" << endl;
    }

    void ChainStack::Pop() {

        if (count == 1) {
            delete top;
            top = NULL;
        }
        head = head->next;
        count--;
        delete top;
        top = head;


    }

    bool ChainStack::JudgeFull() {
        if (count == Maxlength) {
            return 1;
        } else {
            return 0;
        }
    }

    bool ChainStack::JudgeEmpty() {
        if (top == NULL) {
            return 1;
        } else {
            return 0;
        }
    }


