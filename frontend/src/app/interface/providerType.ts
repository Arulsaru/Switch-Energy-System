export interface providerType {
    providerName: String;
    ratePerWatt: Number;
    usersList: Array<String>; // list of users using that provider
    enabled: boolean;
}