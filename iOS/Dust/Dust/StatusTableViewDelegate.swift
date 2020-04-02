//
//  StatusTableViewDelegate.swift
//  Dust
//
//  Created by 임승혁 on 2020/04/02.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class StatusTableViewDelegate: NSObject, UITableViewDelegate {
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        let firstVisibleIndexRow = tableView.indexPathsForVisibleRows?[0].row
        NotificationCenter.default.post(name: .tableViewChangeFirstRow, object: nil, userInfo: ["currentFirstRow":firstVisibleIndexRow ?? 0])
    }
}
