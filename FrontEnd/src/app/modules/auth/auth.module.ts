import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthRoutingModule } from './auth-routing.module';
import { SignUpPageComponent } from './pages/sign-up-page/sign-up-page.component';
import { LogInPageComponent } from './pages/log-in-page/log-in-page.component';
import { SignService } from '../../services/SignService'
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {DialogModule} from 'primeng/dialog';
import { MatFormFieldModule } from "@angular/material/form-field";
import {ButtonModule} from 'primeng/button';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {ToastModule} from 'primeng/toast';
@NgModule({
  imports: [
    CommonModule,MessagesModule,MessageModule,ToastModule,
    ReactiveFormsModule,DialogModule,
    SharedModule,
    AuthRoutingModule,
    MatFormFieldModule,MatSelectModule,
    MatInputModule,ButtonModule
  ],
  declarations: [
    SignUpPageComponent,
    LogInPageComponent
  ],
  providers: [
     
    SignService
    
  ]
})

export class AuthModule {
}
