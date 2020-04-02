//
//  DataTask.swift
//  Dust
//
//  Created by 임승혁 on 2020/03/31.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

class DataTask {
    let defaultSession = URLSession(configuration: .default)
    var dataTask: URLSessionDataTask?
    
    func requestInfoFromNearStation(latitude: Int, longitude: Int) {
        guard var urlComponent = URLComponents(string: "http://13.124.46.74:8080/dust-status") else { return }
        let latitudeQuery = URLQueryItem(name: "latitude", value: String(latitude))
        let longtitudeQuery = URLQueryItem(name: "longitude", value: String(longitude))
        urlComponent.queryItems = [latitudeQuery, longtitudeQuery]
        
        guard let url = urlComponent.url else { return }
        let request = URLRequest(url: url)
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            let decoder = JSONDecoder()
            decoder.dateDecodingStrategy = .formatted(.timeDecodingFormatter)
            
            guard let data = data, let responseData = try? decoder.decode(DustData.self, from: data) else { print("responseDataError"); return; }
            
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .dataLoadComplete, object: nil, userInfo: ["responseData":responseData])
            }
        }
        dataTask!.resume()
    }
}
