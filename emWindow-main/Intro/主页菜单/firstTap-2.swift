//
//  firstTap-2.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/29.
//

import SwiftUI


struct firstTap_2: View {
    @StateObject var data = bookData()
    @State var showModal = false
    
    init() {
        //UINavigationBar.appearance().backgroundColor = .clear
        }
    
    var body: some View {
    
        NavigationView {
            VStack{
                List(data.articles){ article in
                    
                    NavigationLink(destination: bookDDetail(article: article)) {
                        VStack(alignment: .leading){
                            HStack{
                                Text(article.title)
                                    .font(.title3)
                                    .bold()
                                    .padding(.bottom,3)
                                
                                Spacer()
                                passageIcon(cat: article.cat)
                            
                            }
                            
                            Text(article.body)
                                .lineLimit(3)
                            
                        }
                        .padding()
                        
                        
                    }
                    
                }
                .navigationBarTitle("编辑推荐",displayMode:.automatic)
                .toolbar{
                    VStack {
                        Button(action:{
                            showModal.toggle()
                            Vibration.soft.virbrate()
                        }){
                            Image(systemName: "questionmark.circle")
                                .font(.system(size: 50))
                        }.sheet(isPresented: $showModal) {
                            Text("app介绍")
                        }
                        
                    }
                }

            }
        }
    }
}

    
    
    
    
struct firstTap_2_Previews: PreviewProvider {
    static var previews: some View {
        firstTap_2()
    }
}
