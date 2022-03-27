import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import { MuseumService } from '../services/museum.service';
import { Museum } from '../../model/museum.model';
import {MatTableDataSource} from "@angular/material/table";
import {Sort} from "@angular/material/sort";
import {MatDialog} from "@angular/material/dialog";
import {MuseumDetailsComponent} from "../museum-details/museum-details.component";
import {LogService} from "../../service/log.service";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-museums',
  templateUrl: './museums.component.html',
  styleUrls: ['./museums.component.css']
})
export class MuseumsComponent implements OnInit {

  @Output("onDataLoad") public onDataLoad: EventEmitter<any> = new EventEmitter();

  public dataSource: Array<Museum> = new Array<Museum>();
  public filterData: Array<Museum> = new Array<Museum>();
  public displayedColumns: string[] = ['name', 'country', 'city'];

  constructor(private detailsDialog: MatDialog, private service: MuseumService,
              private logService: LogService) { }

  ngOnInit(): void {
    this.loadData();
    this.logService.log(environment.infoCategory, 'museums-tab');
  }

  loadData(){
    this.service.getMuseumData().subscribe((result: any) => {
      const data = result.map((element: any) => {
        return new Museum(element.id, element.name, element.address, element.phoneNumber, element.country, element.city, element.type, element.latitude, element.longitude)
      });
      this.dataSource = data;
      this.filterData = data;
      this.onDataLoad.emit("done");
    });
  }


  applySort(sort: Sort) {
    this.logService.log(environment.infoCategory, 'museums-sort: ' + sort.active);
    const data = this.dataSource.slice();
    if (!sort.active || sort.direction === ''){
      this.dataSource = data;
      return;
    }
    this.dataSource = data.sort((a: Museum,b: Museum) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return this.compare(a.name, b.name, isAsc);
        case 'country': return this.compare(a.country, b.country, isAsc);
        case 'city': return this.compare(a.city, b.city, isAsc);
        default: return  0;
      }
    })
  }

  compare(a: number | string, b: number | string, isAsc: boolean){
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  applyFilter(event: Event) {
    this.dataSource = this.filterData;
    let filter = (event.target as HTMLInputElement).value;
    this.dataSource = this.filterData.filter((museum: Museum) => museum.name.toLowerCase().includes(filter.toLowerCase())
                                                              || museum.city.toLowerCase().includes(filter.toLowerCase()));
  }

  museumClick(row: Museum) {
    let dialogReference = this.detailsDialog.open(MuseumDetailsComponent);
    this.logService.log(environment.infoCategory, 'museum-click: ' + row.id);
    dialogReference.componentInstance.museum = row;
    dialogReference.componentInstance.dialogReference = dialogReference;
  }
}
