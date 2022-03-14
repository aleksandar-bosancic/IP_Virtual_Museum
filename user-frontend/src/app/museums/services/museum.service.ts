import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  constructor(private http: HttpClient) { }

  public getMuseumData() {
    return this.http.get(environment.apiURL + "/museums")
  }
}
