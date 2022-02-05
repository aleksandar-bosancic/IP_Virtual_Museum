import { Component, OnInit } from '@angular/core';
import { MuseumService } from '../services/museum.service';
import { Museum } from '../../model/museum.model';

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.css']
})
export class MuseumsComponent implements OnInit {

  public dataSource = new Array<Museum>();

  constructor(private service: MuseumService) { }

  ngOnInit(): void {
  }

  showData(){
    this.service.getMuseumData().subscribe((result: any) => {
      this.dataSource = result.
    })
  }

}
