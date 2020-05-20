//
//  DustTableCell.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/31.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class DustTableCell: UITableViewCell {
    @IBOutlet weak var statusbar: StatusBarView!
    @IBOutlet weak var ppmStatus: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setStatusBarColor(ppmGrade: String?) {
        guard let grade = ppmGrade else { return }
        switch Int(grade) {
        case 1: self.statusbar.status = .good
        case 2: self.statusbar.status = .normal
        case 3: self.statusbar.status = .bad
        case 4: self.statusbar.status = .critical
        default: self.statusbar.status = .error
        }
    }
    
    func setStatusBarWidth() {
        guard let ppm = Int(ppmStatus.text!) else { return }
        let cellWidth = self.frame.width
        let cellHeight = self.frame.height
        
        let barWidth = Int(cellWidth/200) * Int(ppm)
        
        statusbar.frame = CGRect(x: 0, y: 0, width: barWidth, height: Int(cellHeight))
        self.addSubview(statusbar)
    }
}
