//
//  passageIcon.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/29.
//

import SwiftUI




struct passageIcon: View {
    var cat : String
    var body: some View {
        
        if(cat=="1"){
            HStack{
                Image(systemName: "book.fill")
                    .font(.system(size: 16))
                Text("book")
            }
            .padding(4)
            .background(Color("AccentColor"))
            .foregroundColor(.white)
            .cornerRadius(5)
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
        if(cat=="3"){
            HStack{
                Image(systemName: "mic.fill")
                    .font(.system(size: 16))
                Text("podcast")
            }
            .padding(4)
            .background(Color("AccentColor"))
            .foregroundColor(.white)
            .cornerRadius(5)
        }
    }
}

struct passageIcon_Previews: PreviewProvider {
    static var previews: some View {
        passageIcon(cat:"1")
    }
}

