export interface smartMeterType {
    smartMeterId: String,
    providerName: String,
    userName: String,
    enabled: Boolean,
    epochTime: Number,
    readings: Number,
    date: String,
    smartMeterStatus: String,
}

export interface smartMeterReading {
    smartMeterId: String,
    dateAndTime: String,
    epochTime: Number
}

export interface totalReadings {
    smartMeterId: String,
    total: Number
}