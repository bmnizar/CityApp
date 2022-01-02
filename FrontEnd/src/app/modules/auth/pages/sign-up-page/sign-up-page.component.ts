import { Component } from '@angular/core';
import { transition, trigger, useAnimation } from '@angular/animations';
import { fadeIn } from 'ng-animate';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
 
 
import { RoutesConfig } from '../../../../configs/routes.config';
import { Router } from '@angular/router';
import { SignService } from '../../../../services/SignService';
import {ApplicationUser} from '../../../../entities/ApplicationUser';
import { ApplicationRole } from 'src/app/entities/ApplicationRole';
 
@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss'],
  animations: [
    trigger('fadeIn', [transition('* => *', useAnimation(fadeIn, {
      params: { timing: 1, delay: 0 }
    }))])
  ]
})

export class SignUpPageComponent {
  signUpForm: FormGroup;
  username = new FormControl('', [Validators.required, Validators.maxLength(100)]);
  lastName = new FormControl('', [Validators.required, Validators.maxLength(100)]);
  selectedRole=new FormControl();
  email = new FormControl('', [Validators.required, Validators.email]);
  password = new FormControl('', [Validators.required, Validators.minLength(5)]);
  hide = true;
  displaySingUpFinish: boolean = false;
  availableRoles: string[] = ['ROLE_STAFF', 'ROLE_ADMIN', 'ROLE_ALLOW_EDIT'];
  
  constructor(private formBuilder: FormBuilder,private signService:SignService,
               
              private router: Router 
  ) {
    this.signUpForm = this.formBuilder.group({
      username: this.username,
      selectedRole : this.selectedRole,
      password: this.password
    });
  }

  getErrorMessage(field: any): string | void {
    // @ts-ignore
    const classField: any = this[field]; 
    if (classField?.hasError('required')) { 
      return 'You must enter a value';
    } else if (classField?.hasError('email')) {
      return 'Not a valid email';
    } else if (classField?.hasError('pattern')) {
      return 'Not a valid password';
    }
  }
  goToLogin()
  {
    this.router.navigate([RoutesConfig.routes.auth.logIn]).then(() => {
   
    });
  }
  sendForm() {
    if (this.signUpForm.valid) {
      const formValue = this.signUpForm.value;
      let applicationUser :ApplicationUser =new ApplicationUser();
     let thiss=this;  
      applicationUser.password=formValue.password;
      applicationUser.username=formValue.username;
      applicationUser.applicationRoles =new Array();
      let appRole:ApplicationRole =new ApplicationRole();
      appRole.name=formValue.selectedRole;
      applicationUser.applicationRoles.push(appRole);
      this.signService.register(applicationUser) .subscribe((response: any) => {
        if (!response.errors) {
          thiss.displaySingUpFinish=true;
          /*this.router.navigate([RoutesConfig.routes.auth.logIn]).then(() => {
            this.utilsService.showSnackBar('Cool! Now try to log in!', 'info-snack-bar');
          });*/
        }  
      });
     
  }
  }
}
