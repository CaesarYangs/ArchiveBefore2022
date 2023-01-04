//
//  bookData.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/29.
//

import SwiftUI

struct Article: Codable,Identifiable{
    var id: Int
    var title: String
    var body: String
    var cat: String
}

class bookData: ObservableObject{
    @Published var articles = [Article]()
    
    init(){
        /*let url = URL(string:"http://panel.cyang.site:8888/down/yE9I1D3tM8U0")!
        
        URLSession.shared.dataTask(with:url) { (data,responce,error) in
            DispatchQueue.main.async {
                self.articles = try! JSONDecoder().decode([Article].self, from: data!)
            }
        }.resume()
        */
    }
}
