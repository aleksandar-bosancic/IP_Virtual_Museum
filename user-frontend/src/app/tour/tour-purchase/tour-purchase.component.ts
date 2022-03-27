import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Payment} from "../../model/payment.model";
import {TourService} from "../services/tour.service";
import {Subject, Subscription, takeUntil} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {TicketRequest} from "../../model/ticket-request.model";
import {LogService} from "../../service/log.service";
import {environment} from "../../../environments/environment";


@Component({
  selector: 'app-tour-purchase',
  templateUrl: './tour-purchase.component.html',
  styleUrls: ['./tour-purchase.component.css']
})
export class TourPurchaseComponent implements OnInit, OnDestroy {
  types: string[] = ['VISA', 'MASTERCARD', 'AMERICAN_EXPRESS'];
  months: string[] = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'];
  years: string[] = [];
  public hide: boolean = true;
  formGroup: FormGroup = new FormGroup({});
  private amount: number = 0;
  destroy$: Subject<boolean> = new Subject<boolean>();
  private tourId: number = 0;

  constructor(private formBuilder: FormBuilder, private tourService: TourService, private activatedRoute: ActivatedRoute,
              private route: Router, private logService: LogService) {
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(p => this.tourId = +p['id']);
    this.populateYears();
    this.tourService.tourPrice.pipe(takeUntil(this.destroy$))
      .subscribe((price) => this.amount = price);
    this.formGroup = this.formBuilder.group({
      holderName: [null, Validators.required],
      cardNumber: [null, Validators.required],
      pin: [null, Validators.required],
      type: [null, Validators.required],
      month: [null, Validators.required],
      year: [null, Validators.required]
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  populateYears(): void {
    let date = new Date();
    let year = date.getFullYear();
    this.years.push(String(year));
    for (let i = year; i < year + 10; i++) {
      this.years.push(String(i));
    }
  }

  purchase(): void {
    let validThru = this.formGroup.get('year')?.value + '-' + this.formGroup.get('month')?.value + '-' + '01';
    let payment: Payment = new Payment(this.formGroup.get('holderName')?.value, this.formGroup.get('cardNumber')?.value,
      this.formGroup.get('type')?.value, validThru, this.formGroup.get('pin')?.value, this.amount);
    //Ovaj subscribe nije deprecated iako pise da jeste.
    this.tourService.processPurchase(payment).pipe(takeUntil(this.destroy$)).subscribe(
      (response: any) => {
        this.logService.log(environment.infoCategory, 'tour-purchase-success');
        let username = localStorage.getItem('username');
        if (username != null) {
          this.tourService.getTicket(new TicketRequest(this.tourId, username, response.transactionId)).subscribe((value:any) => {
            this.logService.log(environment.infoCategory, 'ticket-generated: ' + value.id);
          });
        }
        this.route.navigate(['tours']);
      },
      error => {
        this.logService.log(environment.errorCategory, 'tour-purchase-failed');
        alert('Payment was not successful!');
      });
  }
}
