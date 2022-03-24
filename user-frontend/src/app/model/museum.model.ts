export class Museum {
  private _id: number;
  private _name: string;
  private _address: string;
  private _phoneNumber: string;
  private _country: string;
  private _city: string;
  private _type: string;
  private _latitude: number;
  private _longitude: number;


  constructor(id: number, name: string, address: string, phoneNumber: string, country: string, city: string, type: string, latitude: number, longitude: number) {
    this._id = id;
    this._name = name;
    this._address = address;
    this._phoneNumber = phoneNumber;
    this._country = country;
    this._city = city;
    this._type = type;
    this._latitude = latitude;
    this._longitude = longitude;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get address(): string {
    return this._address;
  }

  set address(value: string) {
    this._address = value;
  }

  get phoneNumber(): string {
    return this._phoneNumber;
  }

  set phoneNumber(value: string) {
    this._phoneNumber = value;
  }

  get country(): string {
    return this._country;
  }

  set country(value: string) {
    this._country = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }

  get latitude(): number {
    return this._latitude;
  }

  set latitude(value: number) {
    this._latitude = value;
  }

  get longitude(): number {
    return this._longitude;
  }

  set longitude(value: number) {
    this._longitude = value;
  }
}
