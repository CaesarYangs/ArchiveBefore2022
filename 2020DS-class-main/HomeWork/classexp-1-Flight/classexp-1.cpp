//
// Created by 杨业卿 on 2020/10/4.
// Flight Flight


#include <iostream>
#include <string>
#include "classexp-1-header-Route.h"
#include "classexp-1-header-passenger.h"

void Book(Route&rs,Passenger&rp);

void Refound(Route&rs,Passenger&rp);


int main(){
    Route r;
    Passenger p;
    int marker;
    cout<<"-----------------------"<<endl;
    cout<<"                       "<<endl;
    cout<<" Flight Manage System "<<endl;
    cout<<"                       "<<endl;
    cout<<"-----------------------"<<endl;

    do {
        cout<<"--------------------Main Menu----------------------------"<<endl;
        cout<<"1.Enter Flight Information"<<endl;
        cout<<"2.Load Flight Information from file"<<endl;
        cout<<"3.Save F-I to file"<<endl;
        cout<<"4.Change Flight Information"<<endl;
        cout<<"5.Find Flight"<<endl;
        cout<<"6.Book"<<endl;
        cout<<"7.Refound"<<endl;
        cout<<"8.Load Perchase and Personal Information from file"<<endl;
        cout<<"9.Save P&P-I to file"<<endl;
        cout<<"10.Find Booking Information"<<endl;
        cout<<"11.Show Flight"<<endl;
        cout<<"12.Show Passenger"<<endl;
        cout<<"0.EXIT"<<endl;
        cout<<"---------------------------------------------------------"<<endl;
        cin>>marker;

        switch (marker) {
            case 1:
                r.SetList();
                break;

            case 2:
                r.LoadFromFIle();
                break;

            case 3:
                r.SaveToFile();
                break;

            case 4:
                r.ChangeFlight();
                break;

            case 5:
                r.Find();
                break;

            case 6:
                Book(r,p);
                break;

            case 7:
                Refound(r,p);
                break;

            case 8:
                p.LoadFromFIle();
                break;

            case 9:
                p.SaveToFile();
                break;

            case 10:
                p.Find();
                break;

            case 11:
                r.PrintList();
                break;

            case 12:
                p.ShowPassenger();
                break;


        }
    }while (marker!=0);
    //r.SetList();
    //r.SaveToFile();
    /*r.LoadFromFIle();
    //r.Add();
    r.PrintList();
    //r.FindCity();


    //p.Book();
    //p.Book();

    //Book(r,p);
    p.LoadFromFIle();
    p.ShowPassenger();

    p.ShowPassenger();

    //p.SaveToFile();

    //p.SaveToFile();*/

    return 0;
}

void Book(Route&rs,Passenger&rp){
    cout<<"---Booking------"<<endl;
    string name, id,flight;
    int num;

    cout<<"Enter name:";
    cin>>name;
    cout<<"Enter ID:";
    cin>>id;
    cout<<"Enter Booking number:";
    cin>>num;
    cout<<"Enter Flight ID:";
    cin>>flight;

    if(rs.JudgeBuy(rs.UFindSN(flight),num)==1){
        rp.Book(name,id,num,flight);
        rs.ChangeNum(num,rs.UFindSN(flight));
        cout<<"--Done"<<endl;
    } else{
        cout<<"***Can't operate due to the wrong flight or no ticket***"<<endl;
        return;
    }

}

void Refound(Route&rs,Passenger&rp){
    string name,flight;
    int num;
    rp.Refund(flight,num);
    rs.ChangeNumplus(num,rs.UFindSN(flight));
    cout<<"Refund Success"<<endl;

}