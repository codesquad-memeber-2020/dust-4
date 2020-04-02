//
//  DateFormatterExtension.swift
//  Dust
//
//  Created by 임승혁 on 2020/04/03.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

extension DateFormatter {
    static let timeDecodingFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateFormat = "yyyy-MM-dd HH:mm"
        formatter.calendar = Calendar(identifier: .iso8601)
        formatter.timeZone = TimeZone(secondsFromGMT: 9)
        return formatter
    }()
    
    static let hourMinuteFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateFormat = "HH:mm"
        formatter.calendar = Calendar(identifier: .iso8601)
        formatter.timeZone = TimeZone(secondsFromGMT: 9)
        return formatter
    }()
}
