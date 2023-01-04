//
// Created by 杨业卿 on 2020/10/4.
// Flight Flight


#include <iostream>
#include <string>
#include "classexp-1-header-Route.h"
#include "classexp-1-header-passenger.h"



int main(){
    /*Route r;
    //r.SetList();
    //r.SaveToFile();
    r.LoadFromFIle();
    //r.Add();
    r.PrintList();
    r.FindFSN();
    r.FindCity();*/

    Passenger p;
    p.Book();
    p.Book();
    p.ShowPassenger();

    return 0;
}