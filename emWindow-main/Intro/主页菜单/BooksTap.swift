//
//  BooksTap.swift
//  Intro
//
//  Created by 杨业卿 on 2021/2/8.
//

import SwiftUI

struct BooksTap: View {
    @StateObject var noteData = TabNoteData()
    @State var showModal = false
    
    var body: some View {
        
        NavigationView{
            
                List {
                    ForEach(noteData.notes) { note in
                        NavigationLink(destination: bookDetailPage()){
                        /*GroupBox(label: HStack{
                            //foregroundColor(.blue)
                            Image(systemName: "bookmark")
                            Text(note.title)
                            
                        }){
                            Text(note.content)
                                .lineLimit(2)
                                .padding()
                                
                            //Text(note.writer)
                            //Text(note.date)
                        }
                        
                        .padding()*/
                        VStack(alignment:.leading){
                            HStack {
                                Image(systemName: "bookmark")
                                Text(note.title)
                                    .bold()
                                    .font(.title2)
                            }
                            Text(note.content)
                                .lineLimit(2)
                                .padding()
                        }
                        
                        .listStyle(InsetGroupedListStyle())
                        
                        }
                    
                }
                    .onDelete(perform: delete)
                    
                }
            
            
            
               
                //.listStyle(InsetGroupedListStyle())
            
            // 读取现有笔记
            .navigationBarTitle("书籍列表",displayMode:.automatic)
            .toolbar(content: {
                Button(action:{
                    showModal.toggle()
                    Vibration.soft.virbrate()
                }){
                    Image(systemName: "pencil.tip.crop.circle.badge.plus")
                        .font(.system(size: 80))
                }.sheet(isPresented: $showModal) {
                    addBook(noteData: noteData, showModal: $showModal)
                }
                
            })
        }
        
        
        }
        

    func delete(at offsets: IndexSet) {
        noteData.notes.remove(atOffsets: offsets)
        noteData.saveNotes()
    }

}



struct BooksTap_Previews: PreviewProvider {
    static var previews: some View {
        BooksTap()
    }
}
