//
//  StatusTableViewDataSource.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/31.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class StatusTableViewDataSource: NSObject, UITableViewDataSource {
    var dustData: DustData?
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dustData?.content.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "statusCell", for: indexPath)
        return cell
    }
    

}
