import { Component, OnInit, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { MuseumService } from '../services/museum.service';
import { Museum } from '../../model/museum.model';

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.css']
})
export class MuseumsComponent implements OnInit {

  @Output("onDataLoad") public onDataLoad: EventEmitter<any> = new EventEmitter();

  public dataSource = new Array<Museum>();
  public displayedColumns: string[] = ['name', 'address', 'phoneNumber', 'country', 'city', 'type'];

  constructor(private service: MuseumService) { }

  ngOnInit(): void {
    this.showData();
  }

  showData(){
    this.service.getMuseumData().subscribe((result: any) => {
      console.log(result)
      this.dataSource = result.map((element: any) => {
        return new Museum(element.name, element.address, element.phoneNumber, element.country, element.city, element.type)
      }).slice(0,5);
      this.onDataLoad.emit("done");
    });
  }

}
