import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http'
import {environment} from "../../../environments/environment";
import {Museum} from "../../model/museum.model";
import {AuthService} from "../../auth/services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  public getMuseumData() {
    let header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Digest ${this.authService.getToken()}`)
    }
    return this.http.get(environment.apiURL + "/museums", header)
  }

  public getWeatherData(museum: Museum){
    return this.http.get(environment.forecastUrl + 'lat=' + museum.latitude + '&lon=' + museum.longitude
      + '&APPID=' + environment.weatherApiKey + environment.forecastOptions);
  }

  public getTours(id: number){
    let header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Digest ${this.authService.getToken()}`)
    }
    return this.http.get(environment.apiURL + '/' + id + '/' + 'tours', header);
  }
}
