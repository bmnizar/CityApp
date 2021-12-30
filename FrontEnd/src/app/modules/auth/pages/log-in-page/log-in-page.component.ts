import { Component, ViewChild } from '@angular/core';
import { transition, trigger, useAnimation } from '@angular/animations';
import { fadeIn } from 'ng-animate';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
 
import { RoutesConfig } from '../../../../configs/routes.config';
import { Router } from '@angular/router';
import { StorageService } from '../../../../services/StorageService'; 
import { SignService } from '../../../../services/SignService';
import {ApplicationUser} from '../../../../entities/ApplicationUser';
import {MessageService} from 'primeng/api';
@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.scss'],
  providers: [MessageService],
  animations: [
    trigger('fadeIn', [transition('* => *', useAnimation(fadeIn, {
      params: { timing: 1, delay: 0 }
    }))])
  ]
})

export class LogInPageComponent {
  @ViewChild('loginForm') loginForm: any;

  logInForm: FormGroup;
  username = new FormControl('', [Validators.required ]);
  email = new FormControl('', [Validators.required ]);
  password = new FormControl('', [Validators.required]);
  hide = true;

  constructor(private messageService: MessageService,private formBuilder: FormBuilder,private signService:SignService,
              private storageService: StorageService,
              private router: Router 
  ) {
    this.logInForm = this.formBuilder.group({
      username: this.username, 
      password: this.password  
    });
  }

  getErrorMessage(field: string): string | void {
    // @ts-ignore
    const classField = this[field];
    if (classField?.hasError('required')) {
      return 'You must enter a value';
    } 
  }

  sendForm() {
    if (this.logInForm.valid) {
      const formValue = this.logInForm.value;
      let applicationUser:ApplicationUser =new ApplicationUser();
      applicationUser.username=formValue.username;
      applicationUser.password=formValue.password;
      let thiss=this;
      this.signService.login(applicationUser)
        .subscribe((response: any) => {
          thiss.storageService.setCookie("userIsConnected","true");
          if ( (response =="true")) {
            this.router.navigate([RoutesConfig.routes.hero.myHeroes]);
          } else   {
            thiss.messageService.add({severity:'error', summary:'Wrong username or password', detail:'Login'});
              
          }
        });
    }
  }

}
