import { Observable } from "rxjs";
import { Http, Response, Headers, RequestOptions } from "@angular/http";

import { environment } from "../../environments/environment";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { ApplicationUser } from "../entities/ApplicationUser";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { City } from "../entities/City";

 
@Injectable()
export class CityService {
  constructor(private http: HttpClient) {}
   

  public getCityPictureByName(cityName:String) {
   
    return this.http
      .get(environment.backendUrl + "CityListOperations/getCityPictureByName?cityName="+cityName , {responseType: 'blob'  }  )
     ;
     
  } 
  public listCitiesPerPage(from:number,to:number) {
   
    return this.http
      .get<Array<City>>(environment.backendUrl + "CityListOperations/listCitiesPerPage?from="+from.toString() +"&to="+to.toString() )
     ;
  }

  public updateCityName(cityId:Number,cityNewName:String)
  {
     
    
    return this.http
    .post(environment.backendUrl + "CityListOperations/updateCityName",{  'cityId':cityId.toString(),'cityNewName':cityNewName });
   ;
  }
  public listCitiesPerPageAndFilters(from:number,to:number,  key:string,   operation:string,
      value:string)
   {
    return this.http
    .get<Array<City>>(environment.backendUrl + "CityListOperations/listCitiesPerPageAndFilters?from="+from.toString() +"&to="+to.toString()+"&key="+key.toString()+"&operation="+operation.toString()+"&value="+value.toString() )
   ;
   }
  public listAllCities() {
    
    return this.http
      .get(environment.backendUrl + "CityListOperations/listCities" )
     ;
     
  } 
  public searchByName(appClient: ApplicationUser): Observable<ApplicationUser> {
   
    return this.http
      .post<ApplicationUser>(environment.backendUrl + "AuthenticationRest/signup", appClient )
     ;
  }
  
 
}
