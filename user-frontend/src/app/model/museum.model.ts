export class Museum {
    name: string;
    address: string;
    phoneNumber: string;
    country: string;
    city: string;
    type: string;

    constructor(name: string, address: string, phoneNumber: string, country: string, city: string, type: string){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.type = type;
    }
}
