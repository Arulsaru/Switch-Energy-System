export interface providerType {
    providerName: String;
    ratePerWatt: Number;
    smartMetersList: Array<String>; // list of users using that provider
    enabled: boolean;
}

export interface createProviderType {
    providerName: String;
    ratePerWatt: Number
}