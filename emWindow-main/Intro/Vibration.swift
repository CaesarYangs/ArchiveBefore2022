//
//  Vibration.swift
//  Intro
//
//  Created by 杨业卿 on 2021/1/28.
//

import SwiftUI

enum Vibration {
    case error
    case success
    case selection
    
    case light
    case medium
    case rigid
    case soft
    case heavy
    
    func virbrate(){
        switch self {
        case .error:
            UINotificationFeedbackGenerator().notificationOccurred(.error)
            
        case .success:
            UINotificationFeedbackGenerator().notificationOccurred(.success)
            
        case .selection:
            UISelectionFeedbackGenerator().selectionChanged()
            
        case .light:
            UIImpactFeedbackGenerator(style: .light).impactOccurred()
        
        case .medium:
            UIImpactFeedbackGenerator(style: .medium).impactOccurred()
            
        case .rigid:
            UIImpactFeedbackGenerator(style: .rigid).impactOccurred()
            
        case .soft:
            UIImpactFeedbackGenerator(style: .soft).impactOccurred()
            
        case .heavy:
            UIImpactFeedbackGenerator(style: .heavy).impactOccurred()
       
        }
    }
}
