import { Observable } from "rxjs";
import { Http, Response, Headers, RequestOptions } from "@angular/http";

import { environment } from "../../environments/environment";
import { Injectable } from "@angular/core";
import { map } from "rxjs/operators";
import { ApplicationUser } from "../entities/ApplicationUser";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { City } from "../entities/City";

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};
@Injectable()
export class CityService {
  constructor(private http: HttpClient) {}
  public listCities() {
   
    return this.http
      .get<City>(environment.backendUrl + "AuthenticationRest/listCities" )
     ;
     
  } 
  public searchByName(appClient: ApplicationUser): Observable<ApplicationUser> {
   
    return this.http
      .post<ApplicationUser>(environment.backendUrl + "AuthenticationRest/signup", appClient )
     ;
  }
  
 
}
