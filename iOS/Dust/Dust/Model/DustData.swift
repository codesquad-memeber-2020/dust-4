import Foundation

struct DustData: Codable {
    
    struct DetailData: Codable {
        let dataTime: String
        let pm10Value: String
        let pm10Grade: String
    }
    
    struct Location: Codable {
        let stationName: String
        let addr: String
        let tm: Double
    }
    
    let content: [DetailData]
    let location: Location
}


