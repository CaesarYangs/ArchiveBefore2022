//
//  firstTap.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/28.
//

import SwiftUI


struct firstTap: View {
    @State var inputtest = ""
    @State var showAlert = false
    
    @StateObject var data = bookData()
    var body: some View {
        
        
        NavigationView{
            ScrollView {
                VStack{
                    HStack(alignment:.bottom){
                        Text("欢迎")
                            .font(.system(size:30,weight: .semibold, design: .rounded))
                            .foregroundColor(.accentColor)
                        Spacer()
                        Text("图书推荐")
                        
                    }
                    .padding()
                    .padding(.bottom)
                    
                    
                    
                    HStack {
                        TextField("输入：",text:$inputtest)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                        Spacer()
                        Spacer()
                        Button(action: {showAlert.toggle()}, label: {
                            Text("Button")
                        })
                        .alert(isPresented: $showAlert) {
                            Alert(title: Text(inputtest),message: Text("永久删除，确定要继续吗？"),dismissButton: .default(Text("OK")))
                        }
                        .padding(8)
                        .foregroundColor(.white)
                        .background(Color("AccentColor"))
                        .cornerRadius(10)
                        
                        
                        
                    }
                    .cardStyle2()
                    
                    HStack {
                        Text("占位项目")
                        Spacer()
                        Text("2")
                    }
                    .cardStyle2()
                    
                    HStack {
                        Text("占位项目")
                        Spacer()
                        Text("3")
                    }
                    .cardStyle2()
                    
                    HStack {
                        Text("占位项目")
                        Spacer()
                        Text("4")
                    }
                    .cardStyle2()
                    
                    HStack {
                        Text("占位项目")
                        Spacer()
                        Text("5")
                    }
                    .cardStyle2()
                    
                    HStack {
                        Text("占位项目")
                        Spacer()
                        Text("6")
                    }
                    .cardStyle2()
                    
                    
                
                
                
                }
               .navigationBarTitle("Hello",displayMode:.automatic)
                
            }
        }
    }
}

struct firstTap_Previews: PreviewProvider {
    static var previews: some View {
        firstTap()
    }
}

struct cardStyles:ViewModifier{
    func body(content:Content) -> some View {
        content
            .frame(height:80)
            .padding()
            .background(Color("WBColor"))
            .cornerRadius(15)
            .shadow(radius:3)
            .padding(.horizontal)
            .padding(.bottom)
    }
}

extension View{
    func cardStyle2()->some View{
        self.modifier(cardStyles())
    }
}


