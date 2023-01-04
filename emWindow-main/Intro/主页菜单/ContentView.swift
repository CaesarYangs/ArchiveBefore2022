//
//  ContentView.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/25.
//

import SwiftUI
import MapKit

struct ContentView: View {
    //@AppStorage("darkMode") var colorNow = UITraitCollection.current.userInterfaceStyle
    @Environment(\.colorScheme) var colorSchemeNow
    
    @AppStorage("darkMode") var colorPicker:Int = 0
    
    var body: some View{
        
        TabView{
            firstTap_2()
                .tabItem {
                    
                    Image(systemName: "seal")
                    Text("Main")
                    
                        
                }
            
            //Text("3.电影")
            BooksTap()
                .tabItem {
                    Image(systemName: "book")
                    Text("Books")
                        
                }
            Text("3.电影")
                .tabItem {
                    Image(systemName: "film")
                    Text("Films")
                        
                }
            
            settingpage(colorPicker:$colorPicker)
                .tabItem {
                    Image(systemName: "slider.horizontal.3")
                    Text("Settings")
                        
                }
        }.preferredColorScheme(colorPicker==0 ? nil : colorPicker==1 ? .light : .dark)
        
        
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}



//apple
