//
//  FirstViewController.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/30.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit
import CoreLocation

class FirstViewController: UIViewController, CLLocationManagerDelegate {
    @IBOutlet weak var dustTableView: UITableView!
    @IBOutlet weak var statusView: UIView!
    
    @IBOutlet weak var statusEmoji: EmojiStatusLabel!
    @IBOutlet weak var statusText: TextStatusLabel!
    @IBOutlet weak var ppmText: UILabel!
    @IBOutlet weak var measureDay: UILabel!
    @IBOutlet weak var measureStation: UILabel!
    
    let dataTask = DataTask()
    var locationManager:CLLocationManager!
    var tableViewDataSource = StatusTableViewDataSource()
    var tableViewDelegate = StatusTableViewDelegate()
    var dustData: DustData!
    
    let today = Date()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpLocationManager()
        NotificationCenter.default.addObserver(self, selector: #selector(setStatusUIView), name: .tableViewChangeFirstRow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(setTableView), name: .dataLoadComplete, object: nil)
        self.dustTableView.dataSource = tableViewDataSource
        self.dustTableView.delegate = tableViewDelegate
    }
    
    private func setUpLocationManager() {
        locationManager = CLLocationManager()
        locationManager.delegate = self
        locationManager.requestWhenInUseAuthorization()
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.startUpdatingLocation()
    }
        
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let coor = manager.location?.coordinate{
            dataTask.requestInfoFromNearStation(latitude: Int(coor.latitude), longitude: Int(coor.longitude))
            locationManager.stopUpdatingLocation()
        }
    }
    
    @objc func setStatusUIView(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: Int] else { return }
        let row = notificationInfo["currentFirstRow"]!
        
        let ppmGrade = dustData.content[row].pm10Grade
        let ppmValue = dustData.content[row].pm10Value
        let stationName = dustData.location.stationName
        
        switch Int(ppmGrade) {
        case 1:
            statusEmoji.status = .good
            statusText.status = .good
        case 2:
            statusEmoji.status = .normal
            statusText.status = .normal
        case 3:
            statusEmoji.status = .bad
            statusText.status = .bad
        case 4:
            statusEmoji.status = .critical
            statusText.status = .critical
        default:
            return
        }
        
        ppmText.text = "\(ppmValue) 𝜇g/m³"
        measureStation.text = "\(stationName) 측정소 기준"
        updateDate(dateTime: dustData.content[row].dataTime)
    }
    
    @objc func setTableView(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: DustData] else { return }
        self.dustData = notificationInfo["responseData"]
        self.tableViewDataSource.dustData = dustData
        self.dustTableView.reloadData()
    }
    
    private func updateDate(dateTime: String) {
        let seperateDate = dateTime.components(separatedBy: " ")
        let stringDay = seperateDate[0]
        let stringTime = seperateDate[1]
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.date(from: stringDay)
        
        let interval = Int((date?.timeIntervalSince(today))! / 86400)
        
        if interval == 0 {
            measureDay.text = "오늘 \(stringTime)"
        } else {
            measureDay.text = "어제 \(stringTime)"
        }
    }
}
