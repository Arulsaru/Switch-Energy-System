export interface smartMeterType {
    smartMeterId: String,
    providerName: String,
    userName: String,
    enabled: Boolean,
    epochTime: Number,
    readings: String,
    date: String,
    smartMeterStatus: String,
    electricityReadings: Array<smartMeterReading>
}

export interface smartMeterReading {
    smartMeterId: String,
    dateAndTime: String,
    epochTime: Number
}