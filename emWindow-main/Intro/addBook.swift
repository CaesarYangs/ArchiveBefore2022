//
//  addBook.swift
//  Intro
//
//  Created by 杨业卿 on 2021/2/8.
//

import SwiftUI

struct addBook: View {
    @StateObject var noteData = TabNoteData()
    @State var titleInput = ""
    @State var contentInput = ""
    
    //@StateObject var noteData:bookContentData
    @Binding var showModal: Bool
    @State var nameinput: String = ""
    @State var writerinput:String = ""
    @State var contentinput:String = ""
    @State var timeinput:String = ""
    
    var body: some View {
        
        VStack {
            Text("添加")
                .font(.title)
                .bold()
            // 存储新笔记
            VStack {
                
                HStack{
                    Image(systemName: "text.book.closed")
                        .font(.system(size: 25))
                        .foregroundColor(.accentColor)
                    Spacer()
                    TextField("标题", text: $titleInput)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                }.padding()
                
                HStack{
                    Image(systemName: "pencil")
                        .font(.system(size: 25))
                        .foregroundColor(.accentColor)
                    Spacer()
                    TextField("作者",text: $writerinput)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                }.padding()
                
                HStack{
                    Image(systemName: "newspaper")
                        .font(.system(size: 20))
                        .foregroundColor(.accentColor)
                    Spacer()
                    TextField("正文", text: $contentInput)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                }.padding()
                
                HStack{
                    Image(systemName: "clock.arrow.circlepath")
                        .font(.system(size: 20))
                        .foregroundColor(.accentColor)
                    Spacer()
                    TextField("阅读时间",text: $timeinput)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                }.padding()
                
                HStack {
                    Button(action: {showModal = false
                            Vibration.medium.virbrate()}, label: {
                        Text("          取消         ")
                            .bold()
                            .foregroundColor(.white)
                    })
                        .padding(13)
                        .shadow(radius: 1)
                        .background(Color("Button"))
                        .cornerRadius(10)
                    
                    Spacer()
                    Button(action: {
                        
                        
                        DispatchQueue.global(qos: .background).async {
                            let id = UUID()
                            let newNote = Note(id: id, title: titleInput, content: contentInput,writer: writerinput,date: timeinput)
                            noteData.notes.append(newNote)
                            noteData.saveNotes()
                            titleInput = ""
                            contentInput = ""
                            writerinput = ""
                            timeinput = ""
                            
                        }
                        
                        DispatchQueue.global(qos: .default).async{
                        showModal = false
                        Vibration.medium.virbrate()
                        }
                        
                        
                    }) {
                        //Button(text: "储存笔记", image: "note.text.badge.plus")
                        Text("    储存笔记    ")
                            .foregroundColor(.white)
                    }
                    .padding(13)
                    .shadow(radius: 1)
                    .background(Color("AccentColor"))
                    .cornerRadius(10)
                }
            }
                .padding()
                .background(Color.init(.sRGB, white: 0.9, opacity: 0.2))
                .cornerRadius(10)
                .shadow(radius: 1)
                .padding()
        
        
        
        }
    }
}
       
/*
struct addBook_Previews: PreviewProvider {
  static var previews: some View {
    addBook(noteData: .init(),showModal: .constant(true))
    }
}
*/
