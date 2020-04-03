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
    
    @IBOutlet weak var loadingIndicator: UIActivityIndicatorView!
    
    let dataTask = DataTask()
    var tableViewDataSource = StatusTableViewDataSource()
    var tableViewDelegate = StatusTableViewDelegate()
    var locationManager:CLLocationManager!
    
    var dustData: DustData!
    
    var gradientLayer: CAGradientLayer!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpLocationManager()
        
        NotificationCenter.default.addObserver(self, selector: #selector(setStatusUIView), name: .tableViewChangeFirstRow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(setTableView), name: .dataLoadComplete, object: nil)
        
        self.dustTableView.dataSource = tableViewDataSource
        self.dustTableView.delegate = tableViewDelegate
        self.dustTableView.allowsSelection = false
        
        self.gradientLayer = CAGradientLayer()
         self.loadingIndicator.startAnimating()
    }
    
    private func setUpLocationManager() {
        locationManager = CLLocationManager()
        locationManager.delegate = self
        locationManager.requestLocation()
    }
        
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        if let coor = manager.location?.coordinate {
            dataTask.requestInfoFromNearStation(latitude: Double(coor.latitude), longitude: Double(coor.longitude))
        }
    }
    
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        return
    }
    
    @objc func setStatusUIView(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: Int] else { return }
        let row = notificationInfo["currentFirstRow"]!
    
        let ppmGrade = dustData.content[row].pm10Grade
        let ppmValue = dustData.content[row].pm10Value
        let stationName = dustData.location.stationName
        
        var colors = [CGColor]()
        
        switch Int(ppmGrade) {
        case 1:
            statusEmoji.status = .good
            statusText.status = .good
            colors = [UIColor(named: "goodGradientStart")!.cgColor, UIColor(named: "goodGradientEnd")!.cgColor]
        case 2:
            statusEmoji.status = .normal
            statusText.status = .normal
            colors = [UIColor(named: "normalGradientStart")!.cgColor, UIColor(named: "normalGradientEnd")!.cgColor]
        case 3:
            statusEmoji.status = .bad
            statusText.status = .bad
            colors = [UIColor(named: "badGradientStart")!.cgColor, UIColor(named: "badGradientEnd")!.cgColor]
        case 4:
            statusEmoji.status = .critical
            statusText.status = .critical
            colors = [UIColor(named: "criticalGradientStart")!.cgColor, UIColor(named: "criticalGradientEnd")!.cgColor]
        default:
            return
        }
        
        ppmText.text = "\(ppmValue) 𝜇g/m³"
        measureStation.text = "\(stationName) 측정소 기준"
        updateDate(dateTime: dustData.content[row].dataTime)
        
        changeGradientView(colorArray: colors)
    }
    
    @objc func setTableView(notification: Notification) {
        guard let notificationInfo = notification.userInfo as? [String: DustData] else { return }
        self.dustData = notificationInfo["responseData"]
        self.tableViewDataSource.dustData = dustData
        self.dustTableView.reloadData()
        
        self.loadingIndicator.stopAnimating()
        self.loadingIndicator.isHidden = true
    }
    
    private func updateDate(dateTime: Date) {
        let todayConvertDay = DateFormatter.dayFormatter.string(from: Date())
        let measureConvertDay = DateFormatter.dayFormatter.string(from: dateTime)
        
        let measureTime = DateFormatter.hourMinuteFormatter.string(from: dateTime)
        
        if todayConvertDay == measureConvertDay {
            measureDay.text = "오늘 \(measureTime)"
        } else {
            measureDay.text = "어제 \(measureTime)"
        }

    }
    
    private func changeGradientView(colorArray: [CGColor]) {
        self.gradientLayer.frame = self.view.bounds
        self.gradientLayer.colors = colorArray
        self.view.layer.insertSublayer(self.gradientLayer, at: 0)
    }
}
