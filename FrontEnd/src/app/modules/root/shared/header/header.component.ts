import { Component, Inject, OnInit } from '@angular/core';
import { APP_CONFIG } from '../../../../configs/app.config';
import { NavigationEnd, Router } from '@angular/router';
import { ROUTES_CONFIG, RoutesConfig } from '../../../../configs/routes.config';
import {MessageService} from 'primeng/api';
import { StorageService } from '../../../../services/StorageService';
import { SignService } from '../../../../services/SignService';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  providers: [SignService,StorageService,MessageService],
})

export class HeaderComponent implements OnInit {

  selectedLanguage: string;
  currentUrl: string;
  languages: any[];
  isLoggedIn: boolean;

  constructor(@Inject(APP_CONFIG) public appConfig: any,
              @Inject(ROUTES_CONFIG) public routesConfig: any,private messageService: MessageService,
              private storageService: StorageService,
              private signService: SignService,
              private router: Router) {
    this.selectedLanguage = '';
    this.currentUrl = '';
    this.languages = [{ name: 'en', label: 'English' }, { name: 'es', label: 'Español' }];
    this.isLoggedIn = this.signService.isLoggedIn();
  }

  ngOnInit() {
    this.selectedLanguage = this.storageService.getCookie('language') || 'en';
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentUrl = event.url;
        this.isLoggedIn = this.signService.isLoggedIn();
      }
    });
  }

  changeLanguage(language: string): void {
    this.storageService.setCookie('language', language);
    this.selectedLanguage = language;
  }

  logOut(): void {
 
    this.storageService.removeCookie('userIsConnected');
    this.isLoggedIn = this.signService.isLoggedIn();
    this.router.navigate([RoutesConfig.routes.home]);
  }
  goToCityApp():void
  {
    if(this.signService.isLoggedIn()==false)
    {
      this.messageService.add({severity:'error', summary:'Missing Logging', detail:'You must be logged in'});
           
    }
    else
    {
      this.router.navigate([RoutesConfig.routes.hero.myHeroes]);

    }
    
  }
}
