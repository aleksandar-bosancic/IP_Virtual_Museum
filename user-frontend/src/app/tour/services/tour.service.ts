import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {BehaviorSubject, Observable} from "rxjs";
import {Payment} from "../../model/payment.model";
import {TicketRequest} from "../../model/ticket-request.model";
import {AuthService} from "../../auth/services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class TourService {
  private _tourPrice: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor(private http: HttpClient, private authService: AuthService) { }

  getMediaUrls(id: number){
    let header = {
    headers: new HttpHeaders()
      .set('Authorization',  `Digest ${this.authService.getToken()}`)
  }
    return this.http.get(environment.apiURL + '/media?id=' + id, header);
  }

  getTours(){
    let header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Digest ${this.authService.getToken()}`)
    }
    return this.http.get(environment.apiURL + '/tours/' + localStorage.getItem('username'), header);
  }

  processPurchase(payment: Payment){
    return this.http.post(environment.bankURL + '/payment', payment);
  }

  getTicket(ticketRequest: TicketRequest) {
    let header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Digest ${this.authService.getToken()}`)
    }
    return this.http.post(environment.apiURL + '/generate-ticket', ticketRequest, header);
  }

  get tourPrice(): Observable<number> {
    return this._tourPrice;
  }

  setPrice(value: number) {
    this._tourPrice.next(value);
  }
}
