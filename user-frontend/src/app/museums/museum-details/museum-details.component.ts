import {Component, Input, OnInit} from '@angular/core';
import {Museum} from "../../model/museum.model";
import {MuseumService} from "../services/museum.service";
import {formatDate} from "@angular/common";
import {Tour} from "../../model/tour.model";
import {Router} from "@angular/router";
import {MatDialogRef} from "@angular/material/dialog";
import {TourService} from "../../tour/services/tour.service";
import {environment} from "../../../environments/environment";
import {LogService} from "../../service/log.service";

@Component({
  selector: 'app-museum-details',
  templateUrl: './museum-details.component.html',
  styleUrls: ['./museum-details.component.css']
})
export class MuseumDetailsComponent implements OnInit {

  @Input() public museum: Museum | undefined;
  @Input() public dialogReference: MatDialogRef<any> | undefined;
  public weatherData: any;
  public tours: any;
  public todayDate: Date = new Date();
  public tomorrowDate: Date = new Date(new Date().setDate(this.todayDate.getDate() + 1));
  public dayAfterTomorrowDate: Date = new Date(new Date().setDate(this.todayDate.getDate() + 2));

  constructor(private service: MuseumService, private router: Router, private tourService: TourService,
              private logService: LogService) {
  }

  ngOnInit(): void {
    if(this.museum) {
      this.service.getWeatherData(this.museum).subscribe(res => {
        this.weatherData = res;
      });
      this.service.getTours(this.museum.id).subscribe((res: any) => {
        this.tours = res.map((element: any) => {
          return new Tour(element.id, element.date, element.duration, element.price, element.museumId, element.museumName);
        });
      });
    }
  }

  roundNumber(temperature: string): number {
    return Math.round(Number(temperature));
  }

  getFormattedDate(date: Date): string {
    return formatDate(date, 'EEEE, MMMM d', 'en');
  }

  tourSelected(tour: any) {
    this.logService.log(environment.infoCategory, 'tour-purchase: ' + tour.id)
    this.tourService.setPrice(tour.price);
    // noinspection JSIgnoredPromiseFromCall
    this.router.navigate(['/tours/tour-purchase', tour.id]);
    this.dialogReference?.close();
  }
}
