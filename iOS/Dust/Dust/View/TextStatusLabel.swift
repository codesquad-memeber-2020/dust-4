//
//  TextStatusLabel.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/31.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class TextStatusLabel: UILabel {
    enum Status {
        case good
        case normal
        case bad
        case critical
        case error
    }
    
    var status: Status = .normal {
        didSet {
            setStatus(to: status) }
    }
    
    func setStatus(to status: Status) {
        switch status {
        case .good: self.text = "좋음"
        case .normal: self.text = "보통"
        case .bad: self.text = "나쁨"
        case .critical: self.text = "매우나쁨"
        case .error: self.text = "불러오지 못함"
        }
    }
}
