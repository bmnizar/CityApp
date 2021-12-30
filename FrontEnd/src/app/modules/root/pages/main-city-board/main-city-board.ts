import { Component } from '@angular/core';
import {MessageService} from 'primeng/api';
import { City } from 'src/app/entities/City';
import { CityService } from 'src/app/services/CityService';
import { environment } from "../../../../../environments/environment";
@Component({
  selector: 'main-city-board-page',
  templateUrl: './main-city-board.html',
  styleUrls: ['./main-city-board.scss'],
  providers: [MessageService]
})

export class MainCityBoardComponent {
  uploadUrl:String=environment.backendUrl + "CityListOperations/uploadCityFile";
  listOfCities!:Array<City> ;
  constructor(private messageService: MessageService,private cityService:CityService) {
    let thiss=this;
    this.cityService.listCities()
    .subscribe((response: any) => {
      thiss.listOfCities=response;
    });
  }
  onUploadChangeCityImage(event:any)
  {
    
  }
  onUpload(event:any)
  {
    if(event.originalEvent.body!="OK")
    {
      this.messageService.add({severity:'error', summary:'Upload', detail:'Issue in uploading file '+event.originalEvent.body,life:30000});
    }
    else
    {
    this.messageService.add({severity:'success', summary:'Upload', detail:'File uploaded with success '+event,life:30000});
    }
  }
}
