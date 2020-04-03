//
//  StatusBarView.swift
//  Dust
//
//  Created by 임승혁 on 2020/04/01.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class StatusBarView: UIView {
    enum Status {
        case good
        case normal
        case bad
        case critical
        case error
    }
    
    var status: Status = .normal {
        didSet {
            setStatus(to: status)
        }
    }
    
    func setStatus(to status: Status) {
        switch status {
        case .good: self.backgroundColor = UIColor(named: "goodColor")
        case .normal: self.backgroundColor = UIColor(named: "normalColor")
        case .bad: self.backgroundColor = UIColor(named: "badColor")
        case .critical: self.backgroundColor = UIColor(named: "cirticalColor")
        case .error: self.backgroundColor = UIColor.systemGray
        }
    }
}
