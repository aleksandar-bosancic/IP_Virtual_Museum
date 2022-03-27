import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {TourService} from "../services/tour.service";
import {Media} from "../../model/media.model";
import {DomSanitizer} from "@angular/platform-browser";
import {Tour} from "../../model/tour.model";
import {LogService} from "../../service/log.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrls: ['./tour.component.css']
})
export class TourComponent implements OnInit {
  id: number | undefined;
  media: Media[] = [];
  tours: Tour[] = [];

  constructor(private activatedRoute: ActivatedRoute, private service: TourService, private sanitizer: DomSanitizer,
              private logService: LogService) { }

  ngOnInit(): void {
    this.logService.log(environment.infoCategory, 'tours-tab');
    this.activatedRoute.params.subscribe(p => {
      this.id = +p['id'];
    });
    this.service.getTours().subscribe((result: any) => {
      this.tours = result.map((element: any) => {
        return new Tour(element.id, element.date, element.duration, element.price, element.museumId, element.museumName);
      })
    })
    if (this.id) {
      this.service.getMediaUrls(this.id).subscribe((result: any) =>{
        this.media = result.map((element: any) => {
          return new Media(element.id, element.museumId, element.url, element.video, this.sanitizer);
        })
      });
    }
  }

  loadTour(id: number) {
    this.logService.log(environment.infoCategory, 'tour-selected: ' + id);
    this.service.getMediaUrls(id).subscribe((result: any) =>{
      this.media = result.map((element: any) => {
        return new Media(element.id, element.museumId, element.url, element.video, this.sanitizer);
      })
    });
    this.isMediaEmpty()
  }

  isMediaEmpty(){
    return this.media.length == 0;
  }
}
