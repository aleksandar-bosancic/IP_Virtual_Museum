import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {BehaviorSubject, Observable} from "rxjs";
import {Payment} from "../../model/payment.model";
import {TicketRequest} from "../../model/ticket-request.model";

@Injectable({
  providedIn: 'root'
})
export class TourService {
  private _tourPrice: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor(private http: HttpClient) { }

  getMediaUrls(id: number){
    return this.http.get(environment.apiURL + '/media?id=' + id);
  }

  getTours(){
    return this.http.get(environment.apiURL + '/tours/' + localStorage.getItem('username'));
  }

  processPurchase(payment: Payment){
    return this.http.post(environment.bankURL + '/payment', payment);
  }

  getTicket(ticketRequest: TicketRequest) {
    return this.http.post(environment.apiURL + '/generate-ticket', ticketRequest);
  }

  get tourPrice(): Observable<number> {
    return this._tourPrice;
  }

  setPrice(value: number) {
    this._tourPrice.next(value);
  }
}
