//
//  AboutListView.swift
//  iosApp
//
//  Created by Тарас Вовченко on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//
import shared
import SwiftUI

struct AboutListView: View {
    
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }
    
    private let items: [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
        var result: [RowItem] = [
            .init(title: "Operation System", subtitle: "\(platform.osName) \(platform.osVersion)"),
            .init(title: "Device", subtitle: platform.deviceModel),
            .init(title: "Density", subtitle: "@\(platform.density)x"),
        ]
        
        return result
    }()
    
    var body: some View {
        List {
            ForEach(items, id: \.self) { item in
                VStack(alignment: .leading) {
                    Text(item.title).font(.footnote).foregroundStyle(.secondary)
                    Text(item.subtitle).font(.body).foregroundStyle(.primary)
                }.padding(.vertical, 4)
            }
        }
    }
}

#Preview {
    AboutListView()
}
