//
//  settingpage.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/28.
//

import SwiftUI





struct settingpage: View {
    @StateObject var noteData = TabNoteData()
    @State var showAlert = false
    @State var toggleValue1 = false
    @State var toggleValue2 = false
    @State var toggleValue3 = false
   
    @State var showSettingModal = false
    @Binding var colorPicker: Int
    var colorPickerOptions = ["跟随系统", "浅色", "深色"]
    
   
    
    var body: some View {
        NavigationView{
            Form {
                Section(header: Text("应用设置")){
                    VStack(alignment:.leading){
                        Text(" 颜色模式")
                        Picker("颜色模式", selection: $colorPicker) {
                                ForEach(0..<colorPickerOptions.count) { index in
                                    Text(colorPickerOptions[index]).tag(index)
                                }
                            
                            }.pickerStyle(SegmentedPickerStyle())
                    }.padding(.bottom)
                    .padding(.top)
                    
                    Toggle("系统通知",isOn:$toggleValue1)
                        .toggleStyle(SwitchToggleStyle(tint: .accentColor))
                    
                    Toggle("启用iCloud",isOn:$toggleValue2)
                        .toggleStyle(SwitchToggleStyle(tint: .accentColor))
                    
                    Toggle("Face ID保护",isOn:$toggleValue3)
                        .toggleStyle(SwitchToggleStyle(tint: .accentColor))
                    
                    Button(action: {
                            Vibration.heavy.virbrate()
                        if noteData.remove(){
                            showAlert.toggle()
                        }}, label: {
                        Text("清空书籍数据")
                            
                            //.bold()
                    })
                    .alert(isPresented: $showAlert) {
                            Alert(title: Text("删除"), message: Text("删除操作已完成 重启应用以继续"),
                                  dismissButton:.default(Text("好的"),action: {
                                    exit(1)
                                  })
                           
                    )}
                    
                    
                }
                
                Section(header: Text("帮助与反馈")){
                    Button(action: {
                       
                    }, label: {
                        Text("帮助指南")
                        
                            //.bold()
                    })
                    Button(action: /*@START_MENU_TOKEN@*/{}/*@END_MENU_TOKEN@*/, label: {
                        Text("反馈")
                            //.bold()
                    })
                }
                
                Section(header: Text("关于")){
                    Button(action: {
                            //Vibration.selection.virbrate()
                            Vibration.success.virbrate()
                            //Vibration.light.virbrate()
                            showSettingModal.toggle()}, label: {
                        Text("关于开发者")
                            //.bold()
                            .foregroundColor(.accentColor)
                    })
                    .sheet(isPresented: $showSettingModal, content: {
                        Text("Developed by")
                            .font(.footnote)
                        Text("Caesar Yang")
                            .font(.headline)
                            .shadow(radius: 1)
                        Text("2021")
                            .font(.footnote)
                    })
                    
                    
                    
                    Button(action: {
                            Vibration.selection.virbrate()
                            showSettingModal.toggle()}, label: {
                        Text("关于空窗")
                            //.bold()
                            .foregroundColor(.accentColor)
                    })
                    .sheet(isPresented: $showSettingModal, content: {
                        Text("檐前数片无人扫，又得书窗一夜明。")
                            .font(.custom("HYShangWeiShouShuW", size: 20))
                          
                    })
                }
                
            }
            
            
            .navigationBarTitle("设置",displayMode:.automatic)
            
        }
        
        
        
    }
}

struct settingpage_Previews: PreviewProvider {
    static var previews: some View {
        settingpage(colorPicker: .constant(1))
    }
}

//
