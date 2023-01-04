//
//  bookContentFile.swift
//  Intro
//
//  Created by 杨业卿 on 2021/2/8.
//

import Foundation

//book data model
struct bookContent: Codable,Identifiable {
    var id: UUID
    var name: String
    var writer: String
    var content: String
    var date: String
    //var pic:pic
    
    
}


class bookContentData: ObservableObject{
    @Published var books: [bookContent]=[]
    
    static let SandboxURL = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
    let booksDataURL = SandboxURL.appendingPathComponent("booksData2.json")
    
    init() {
        books = getBookData()
    }
    
    
    func getBookData() -> [bookContent]{
        var results: [bookContent] = []
        
        if FileManager.default.fileExists(atPath: booksDataURL.path) {
            let data = try! Data(contentsOf: booksDataURL)
            results = try! JSONDecoder().decode([bookContent].self, from: data)
        }
        return results
    }
    
    func saveBookData(){
        DispatchQueue.global(qos: .userInitiated).async {
            let data = try? JSONEncoder().encode(self.booksDataURL)
            try? data?.write(to: self.booksDataURL)
        }
        
    }
}


// Model
struct Note: Codable, Identifiable {
    var id: UUID
    var title: String
    var content: String
    var writer: String
    var date: String
}

// ModelView
class TabNoteData: ObservableObject {
    @Published var notes: [Note] = []
    
    static let sandboxURL = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
    let notesURL = sandboxURL.appendingPathComponent("notes2.json")
    
    
    
    init() {
        notes = getNotes()
    }
    
    func getNotes() -> [Note] {
        var result: [Note] = []
        if FileManager.default.fileExists(atPath: notesURL.path) {
            let data = try! Data(contentsOf: notesURL)
            result = try! JSONDecoder().decode([Note].self, from: data)
        }
        return result
    }
    
    func saveNotes() {
        DispatchQueue.global(qos: .userInitiated).async {
            let data = try? JSONEncoder().encode(self.notes)
            try? data?.write(to: self.notesURL)
        }
    }
    
    func remove()->Bool {
        if ((try? FileManager.default.removeItem(at: notesURL)) != nil){
            return true
        }else{
            return false
        }
    }
}
