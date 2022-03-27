import {Component, OnInit} from '@angular/core';
import {NewsService} from "../services/news.service";
import {LogService} from "../../service/log.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public path: any;
  public newsData: any;

  constructor(public service: NewsService, private logService: LogService) {
  }

  ngOnInit(): void {
    this.service.getNewsFeed().subscribe((res: any) => {
      this.newsData = res.items.slice(5);
    })
    this.logService.log(environment.infoCategory, 'home-tab');
  }

  isLoggedIn() {
    return localStorage.getItem('isLoggedIn');
  }
}
