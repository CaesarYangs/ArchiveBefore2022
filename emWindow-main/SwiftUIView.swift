//
//  SwiftUIView.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/27.
//

import SwiftUI
import MapKit

struct SwiftUIView: View {
    //变量声明
    @State var toggleValue = true
    @State var pickerValue = 0
    @State var sliderValue = 0.0
    @State var stepperValue = 0
    @State var selectedDate = Date()
    @State var progressValue = 0.5
    @State var location = MKCoordinateRegion(center: .init(latitude: 39.9130, longitude: 116.3907), latitudinalMeters: 1000, longitudinalMeters: 1000)

    
    var pickerOptions = ["书","电影","音乐"]
    var dateRange: ClosedRange<Date> {
        let min = Calendar.current.date(byAdding: .day, value: -100, to: Date())!
        let max = Calendar.current.date(byAdding: .day, value: 100, to: Date())!
        return min...max
    }
    
    
    
    
    
    
    
    var body: some View {
        ScrollView{
            
            HStack{
                //开关
                VStack(alignment:.leading){
                    Text("Toggle")
                        .font(.headline)
                    Toggle("自动登录",isOn:$toggleValue)
                        .toggleStyle(SwitchToggleStyle(tint: .accentColor))
                }
                .frame(height:80)
                .padding()
                .background(Color("WBColor"))
                .cornerRadius(15)
                .shadow(radius:3)
                .padding(.horizontal)
                .contextMenu(ContextMenu(menuItems: {
                    Text("这是Toggle开关组件")
                    /*@START_MENU_TOKEN@*/Text("Menu Item 2")/*@END_MENU_TOKEN@*/
                    /*@START_MENU_TOKEN@*/Text("Menu Item 3")/*@END_MENU_TOKEN@*/
                }))
                
                //开关
                VStack(alignment:.leading){
                    Text("Button")
                        .font(.headline)
                    Button(action: /*@START_MENU_TOKEN@*/{}/*@END_MENU_TOKEN@*/, label: {
                        Image(systemName: "arrowshape.turn.up.right.circle")
                            .font(.system(size: 16,weight: .bold))
                        Text("下一页")
                            .bold()
                        
                    })
                    .padding(10)
                    .foregroundColor(Color.white)
                    .background(Color("AccentColor"))
                    .cornerRadius(15)
                    
                }
                .frame(height:80)
                .padding()
                .background(Color("WBColor"))
                .cornerRadius(15)
                .shadow(radius:3)
                .padding(.trailing)
            }
            .padding(.bottom)
            
            
            //选择器
            VStack(alignment:.leading){
                Text("Picker")
                    .font(.headline)
                Picker("Picker", selection: $pickerValue) {
                        ForEach(0..<pickerOptions.count) { index in
                            Text(pickerOptions[index]).tag(index)
                        }
                    }.pickerStyle(SegmentedPickerStyle())
                
            }
            .cardStyle()
            
            
            //滑动条
            VStack(alignment:.leading){
                Text("Sliders")
                    .font(.headline)
                HStack{
                    Image(systemName: "light.min")
                        .font(.system(size: 18,weight: .bold))
                    
                    Slider(value: $sliderValue, in: -5...5, step: 0.1)
                    
                    Image(systemName: "light.max")
                        .font(.system(size: 18,weight: .bold))
                }
                
                    
            }
            .cardStyle()
            
            //梯度控制
            VStack(alignment:.leading){
                Text("Stepper")
                    .font(.headline)
                Stepper("当前数值： \(stepperValue)", value: $stepperValue, in: -5...5)
            }
            .cardStyle()
            
            //日期选择
            VStack{
                Text("Date Picker")
                    .font(.headline)
                DatePicker(
                        selection: $selectedDate, in: dateRange,
                        displayedComponents: [.hourAndMinute, .date],
                        label: {
                            Text("日期:")
                        })
            }
            .cardStyle()
            
            HStack{
                //二级菜单
                VStack(alignment:.center){
                    Text("Context")
                        .font(.headline)
                    VStack{
                        Image(systemName: "filemenu.and.selection")
                            .font(.system(size:40,weight:.bold))
                            .foregroundColor(.accentColor)
                            .frame(width:80, height:80)
                        Text("长按菜单")
                            .font(.headline)
                    }
                    .contextMenu(ContextMenu(menuItems: {
                        Button("YYQ"){}
                        Button("厉害"){}
                        Button("哈哈哈"){}
                    }))
                }
                .frame(height:120)
                .padding()
                .background(Color("WBColor"))
                .cornerRadius(15)
                .shadow(radius:3)
                //.padding(.trailing)
                .padding(.horizontal)
                
                
                
                VStack(alignment:.center){
                    Text("")
                        .font(.headline)
                    Spacer()
                    ProgressView(value: progressValue)
                        .progressViewStyle(CircularProgressViewStyle())
                    Spacer()
                }
                .frame(height:120)
                .padding()
                .background(Color("WBColor"))
                .cornerRadius(15)
                .shadow(radius:3)
                .padding(.horizontal)
                
                
                
                VStack(alignment:.center){
                    Text("Map")
                        .font(.headline)
                    Map(coordinateRegion: $location)
                        .frame(width:100,height: 100,alignment: .center)
                        .cornerRadius(15)
                    
                }
                .frame(height:120)
                .padding()
                .background(Color("WBColor"))
                .cornerRadius(15)
                .shadow(radius:3)
                //.padding(.trailing)
                .padding(.horizontal)
                
                
            }
            //.padding(.infinity)
            //.padding(.horizontal)
            .padding(.bottom)
            
            
            
        }
        
        
    }
}

struct SwiftUIView_Previews: PreviewProvider {
    static var previews: some View {
        SwiftUIView()
    }
}



struct Styles:ViewModifier{
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
    func cardStyle()->some View{
        self.modifier(Styles())
    }
}
