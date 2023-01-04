//
//  bookDDetail.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/29.
//

import SwiftUI

struct bookDDetail: View {
    var article :Article
    var body: some View {
        ScrollView {
            passageIcon(cat: article.cat)
            Text(article.body)
                .padding()
            
                
        }.navigationTitle(article.title)
    }
}

struct bookDDetail_Previews: PreviewProvider {
    static var previews: some View {
        bookDDetail(article: Article(id: 1, title: "标题", body: "正文",cat:"1"))
    }
}
