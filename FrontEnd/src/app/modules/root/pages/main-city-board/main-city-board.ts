import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { City } from 'src/app/entities/City';
import { CityService } from 'src/app/services/CityService';
import { environment } from "../../../../../environments/environment";
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { ApplicationUser } from 'src/app/entities/ApplicationUser';
import { LazyLoadEvent } from 'primeng/api';
@Component({
  selector: 'main-city-board-page',
  templateUrl: './main-city-board.html',
  styleUrls: ['./main-city-board.scss'],
  providers: [MessageService]
})

export class MainCityBoardComponent {
  uploadUrl: String = environment.backendUrl + "CityListOperations/uploadCityFile";
  listOfCities!: Array<City>;
  static DEFAULT_CITY_ROWS_TO_FETCH_LAZY: number = 10;
  totalRecords: number = 0;
  loading: boolean = true;
  intialLoadIsDone: Boolean = false;
  constructor(private messageService: MessageService, private sanitizer: DomSanitizer, private cityService: CityService) {
    this.loadTablePerPage(0, MainCityBoardComponent.DEFAULT_CITY_ROWS_TO_FETCH_LAZY);
  }

  onLoadCityTable(event: any) {
    if ((this.intialLoadIsDone == false)) {
      return;
    }

    this.loading = true;
    let thiss = this;
    let pageIndex = event.first / event.rows;
    let matchMode!: string;
    let value!: string;
    if (event.filters.name.length > 0) {
      matchMode = event.filters.name[0].matchMode;
      value = event.filters.name[0].value;
      if (value == null) {
        this.cityService.listCitiesPerPage(pageIndex, 10)
          .subscribe((response: any) => {
            thiss.listOfCities = response;
            thiss.loadCityImage(response);
            thiss.loading = false;

          });
      }
      else {
        this.cityService.listCitiesPerPageAndFilters(pageIndex, 10, "name", matchMode, value)
          .subscribe((response: any) => {
            thiss.listOfCities = response;
            thiss.loadCityImage(response);
            thiss.loading = false;

          });
      }



    }
  }
  loadTablePerPage(from: any, to: any) {
    let thiss = this;
    this.cityService.listCitiesPerPage(from, to)
      .subscribe((response: Array<City>) => {
        thiss.listOfCities = response;
        thiss.intialLoadIsDone = true;
        thiss.loadCityImage(response);
        if (response.length > 0) {
          thiss.totalRecords = response[0].totalRows;
        }

        thiss.loading = false;

      });
  }
  loadCityImage(response: any) {

    for (let city of response) {
      this.cityService.getCityPictureByName(city.name)
        .subscribe((response2: any) => {
          const blob = new Blob([response2], { type: 'application/image' });

          let objectUrl = window.URL.createObjectURL(blob);
          city.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectUrl);


        });
    }

  }
  isEditRoleUser() {
    var conntectedUserJson = localStorage.getItem('connectedUser');
    if (conntectedUserJson != null) {
      var applicationUser: ApplicationUser = JSON.parse(conntectedUserJson);

      var applicationRoles = applicationUser.applicationRoles;
      if (applicationRoles != null) {

        for (let applicationRole of applicationRoles) {
          if (applicationRole.name == 'ROLE_ALLOW_EDIT') {
            return false;
          }
        }
      }
    }

    return true;
  }
  onEditCityName(event: any) {
    let thiss = this;
    this.cityService.updateCityName( event.data.id,event.data.name)
      .subscribe((response2: any) => {
        
        thiss.loadTablePerPage(0, MainCityBoardComponent.DEFAULT_CITY_ROWS_TO_FETCH_LAZY);

      });
  }
  onUploadChangeCityImage(event: any) {

  }
  onUpload(event: any) {
    if (event.originalEvent.body != "OK") {
      this.messageService.add({ severity: 'error', summary: 'Upload', detail: 'Issue in uploading file ' + event.originalEvent.body, life: 30000 });
    }
    else {
      this.messageService.add({ severity: 'success', summary: 'Upload', detail: 'File uploaded with success ', life: 30000 });
      this.loadTablePerPage(0, MainCityBoardComponent.DEFAULT_CITY_ROWS_TO_FETCH_LAZY);
    }
  }
}
