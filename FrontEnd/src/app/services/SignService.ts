import { Observable } from "rxjs";
import { Http, Response, Headers, RequestOptions } from "@angular/http";

import { environment } from "../../environments/environment";
import { Injectable } from "@angular/core"; 
import { ApplicationUser } from "../entities/ApplicationUser";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import jwt_decode from 'jwt-decode';
import { StorageService } from './StorageService'; 
 
@Injectable()
export class SignService {
  constructor(private http: HttpClient,private storageService: StorageService) {}
  public login(appClient: ApplicationUser) {
   
    return this.http
      .post<Boolean>(environment.backendUrl + "AuthenticationRest/login", appClient);
     
  } 
  signOff() {
    this.storageService.removeCookie('userIsConnected');
  }
  isLoggedIn(): boolean {
    try {
      const token = this.storageService.getCookie('userIsConnected');
      if (token=='true') {
        return true;
      }
      return false;
    } catch (Error) {
      return false;
    }
  }
  public register(appClient: ApplicationUser) {
   
    return this.http
      .post<String>(environment.backendUrl + "AuthenticationRest/signup", appClient )
     ;
  }
  
 
}
