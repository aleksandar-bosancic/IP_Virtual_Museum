export class Payment {
  public holderName: string;
  public number: string;
  public type: string;
  public validThru: string;
  public pin: string;
  public amount: number;

  constructor(holderName: string, number: string, type: string, validThru: string, pin: string, amount: number) {
    this.holderName = holderName;
    this.number = number;
    this.type = type;
    this.validThru = validThru;
    this.pin = pin;
    this.amount = amount;
  }
}
