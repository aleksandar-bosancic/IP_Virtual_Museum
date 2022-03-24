export class Tour {
  private _id: number;
  private _date: Date;
  private _duration: number;
  private _price: number;
  private _museumId: number;
  private _museumName: string;


  constructor(id: number, date: Date, duration: number, price: number, museumId: number, museumName: string) {
    this._id = id;
    this._date = date;
    this._duration = duration;
    this._price = price;
    this._museumId = museumId;
    this._museumName = museumName;
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get date(): Date {
    return this._date;
  }

  set date(value: Date) {
    this._date = value;
  }

  get duration(): number {
    return this._duration;
  }

  set duration(value: number) {
    this._duration = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

  get museumId(): number {
    return this._museumId;
  }

  set museumId(value: number) {
    this._museumId = value;
  }

  get museumName(): string {
    return this._museumName;
  }

  set museumName(value: string) {
    this._museumName = value;
  }
}
