//
//  EmojiStatusLabel.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/31.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class EmojiStatusLabel: UILabel {
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
        case .good: self.text = "😀"
        case .normal: self.text = "🙂"
        case .bad: self.text = "😷"
        case .critical: self.text = "😱"
        case .error: self.text = "🤯"
        }
    }
}
