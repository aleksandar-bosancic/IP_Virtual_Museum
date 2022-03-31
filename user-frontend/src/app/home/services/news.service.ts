import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(public http: HttpClient) { }

  getNewsFeed() {

    return this.http.get(environment.rssToJsonUrl + environment.newsFeedUrl, { headers: { } });
  }
}
