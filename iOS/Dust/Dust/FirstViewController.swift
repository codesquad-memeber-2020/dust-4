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
    @IBOutlet weak var dustTableView: StatusTableView!
    @IBOutlet weak var statusView: StatusView!
    
    let dataTask = DataTask()
    var locationManager:CLLocationManager!
    var tableViewDataSource = StatusTableViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpLocationManager()
        self.dustTableView.dataSource = tableViewDataSource
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
            dataTask.requestInfoFromNearStation(latitude: Int(coor.latitude), longitude: Int(coor.longitude)) { (DustData) in
                self.tableViewDataSource.dustData = DustData
                self.dustTableView.reloadData()
            }
        }
        locationManager.stopUpdatingLocation()
    }
    
}
