import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class MuseumService {

  constructor(private http: HttpClient) { }

  public getMuseumData(session: string) {
    return this.http.get("http://localhost:9000/museums")
  }
}
