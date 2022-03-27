export class TicketRequest {
  public tourId: number;
  public username: string;
  public transactionId: number;


  constructor(tourId: number, username: string, transactionId: number) {
    this.tourId = tourId;
    this.username = username;
    this.transactionId = transactionId;
  }
}
