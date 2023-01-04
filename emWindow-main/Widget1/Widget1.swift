//
//  Widget1.swift
//  Widget1
//
//  Created by 杨业卿 on 2021/4/23.
//

import WidgetKit
import SwiftUI
import Intents

struct Provider: IntentTimelineProvider {
    func placeholder(in context: Context) -> SimpleEntry {
        SimpleEntry(date: Date(), configuration: ConfigurationIntent(),bookname:"bookname", writer:"writer", sentence:"s", time:"time")
    }

    func getSnapshot(for configuration: ConfigurationIntent, in context: Context, completion: @escaping (SimpleEntry) -> ()) {
        let entry = SimpleEntry(date:Date(), configuration:configuration, bookname:"s", writer:"time", sentence: "aaa", time: "ttt")
        completion(entry)
    }

    func getTimeline(for configuration: ConfigurationIntent, in context: Context, completion: @escaping (Timeline<Entry>) -> ()) {
        var entries: [SimpleEntry] = []

        // Generate a timeline consisting of five entries an hour apart, starting from the current date.
        let bn = "hello"
        let wr = "wr"
        let sen = "xxx"
        let t = "t"
        let currentDate = Date()
        for hourOffset in 0 ..< 5 {
            let entryDate = Calendar.current.date(byAdding: .hour, value: hourOffset, to: currentDate)!
            let entry = SimpleEntry(date: currentDate, configuration: configuration, bookname:bn, writer:wr, sentence: sen, time: t)
            entries.append(entry)
        }

        let timeline = Timeline(entries: entries, policy: .atEnd)
        completion(timeline)
    }
}

struct SimpleEntry: TimelineEntry {
    let date: Date
    let configuration: ConfigurationIntent
    let bookname: String
    let writer: String
    let sentence: String
    let time: String
}

struct Widget1EntryView : View {
    var entry: Provider.Entry

    var body: some View {
        HStack{
            VStack{
                Text("Now Reading")
                    .font(.caption)
                Text(entry.bookname)
                Text(entry.date, style: .date)
                
            }
            .padding(.horizontal,10)
            VStack{
                Text(entry.sentence)
                    .font(.system(size: 11))
            }
            .padding(.vertical,20)
            .background(Color(.gray))
            .cornerRadius(10)
        }
        
        
    }
}

@main
struct Widget1: Widget {
    let kind: String = "Widget1"

    var body: some WidgetConfiguration {
        IntentConfiguration(kind: kind, intent: ConfigurationIntent.self, provider: Provider()) { entry in
            Widget1EntryView(entry: entry)
        }
        .configurationDisplayName("空窗 小组件")
        .description("移动设备上的窗户")
    }
}

struct Widget1_Previews: PreviewProvider {
    static var previews: some View {
        Widget1EntryView(entry: SimpleEntry(date: Date(), configuration: ConfigurationIntent(),bookname: "失明症漫记",writer: "若泽•萨拉马戈",sentence: "如果你看，就要看见。 如果你能看见，就要仔细观察。",time: "2021.1.15"))
            .previewContext(WidgetPreviewContext(family: .systemMedium))
    }
}
