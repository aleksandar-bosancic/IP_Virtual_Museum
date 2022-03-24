import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {environment} from "../../../environments/environment";
import {Museum} from "../../model/museum.model";

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  constructor(private http: HttpClient) { }

  public getMuseumData() {
    return this.http.get(environment.apiURL + "/museums")
  }

  public getWeatherData(museum: Museum){
    return this.http.get(environment.forecastUrl + 'lat=' + museum.latitude + '&lon=' + museum.longitude
      + '&APPID=' + environment.weatherApiKey + environment.forecastOptions);
  }

  public getTours(id: number){
    return this.http.get(environment.apiURL + '/' + id + '/' + 'tours');
  }
}
