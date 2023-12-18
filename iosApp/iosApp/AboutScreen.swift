//
//  AboutScreen.swift
//  iosApp
//
//  Created by Тарас Вовченко on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutListView().navigationTitle("About Device")
        }
    }
}

#Preview {
    AboutScreen()
}
