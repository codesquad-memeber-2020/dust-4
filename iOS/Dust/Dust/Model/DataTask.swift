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
    
    func requestInfoFromNearStation(latitude: Int, longitude: Int, completion: @escaping (DustData)-> Void) {
        guard var urlComponent = URLComponents(string: "http://ec2-15-164-216-75.ap-northeast-2.compute.amazonaws.com:8080/dust-status") else { return }
        
        let latitudeQuery = URLQueryItem(name: "latitude", value: String(latitude))
        let longtitudeQuery = URLQueryItem(name: "longitude", value: String(longitude))
        urlComponent.queryItems = [latitudeQuery, longtitudeQuery]
        guard let url = urlComponent.url else { return }
        let request = URLRequest(url: url)
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(DustData.self, from: data) else { print("responseDataError"); return; }
            
            DispatchQueue.main.async {
                completion(responseData)
            }
        }
        dataTask!.resume()
    }
}
