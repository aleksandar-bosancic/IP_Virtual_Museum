import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TourService {

  constructor(private http: HttpClient) { }

  getMediaUrls(id: number){
    return this.http.get(environment.apiURL + '/media?id=' + id);
  }

  getTours(){
    return this.http.get(environment.apiURL + '/tours')
  }
}
