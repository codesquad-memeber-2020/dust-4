//
//  FirstViewController.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/30.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class FirstViewController: UIViewController {

    @IBOutlet weak var emojiLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.emojiLabel.text = "😄"
    }


}

