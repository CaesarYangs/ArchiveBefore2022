//
//  startcards.swift
//  Intro
//
//  Created by 杨业卿 on 2021/4/25.
//

import SwiftUI



struct startCard: View {
    var cat : String
    var names : String
    var body: some View {
        if(cat=="1"){
            VStack{
                Text("阅读中：")
                Text(names)
                Text("阅读时间：")
                
            }
            .padding(100)
            .background(Color("AccentColor"))
            .foregroundColor(.white)
            .cornerRadius(25)
        }
        if(cat=="2"){
            HStack{
                Image(systemName: "film.fill")
                    .font(.system(size: 16))
                Text("movie")
            }
            .padding(4)
            .background(Color("AccentColor"))
            .foregroundColor(.white)
            .cornerRadius(5)
        }
    }
}

struct startCard_Previews: PreviewProvider {
    static var previews: some View {
        startCard(cat:"1",names:"啦啦啦")
    }
}

