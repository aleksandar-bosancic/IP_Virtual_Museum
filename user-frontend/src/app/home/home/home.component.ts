import {Component, OnInit} from '@angular/core';
import {NewsService} from "../services/news.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public path: any;
  public newsData: any;

  constructor(public service: NewsService) {
  }

  ngOnInit(): void {
    this.service.getNewsFeed().subscribe((res: any) => {
      this.newsData = res.items.slice(5);
    })
  }

  isLoggedIn() {
    return localStorage.getItem('isLoggedIn');
  }
}
