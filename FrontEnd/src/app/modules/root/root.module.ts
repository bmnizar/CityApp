import { NgModule } from '@angular/core';
import { HeaderComponent } from './shared/header/header.component';
import { SearchBarComponent } from './shared/search-bar/search-bar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { MainCityBoardComponent } from './pages/main-city-board/main-city-board';
import { RootRoutingModule } from './root-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {FileUploadModule} from 'primeng/fileupload';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import {ToastModule} from 'primeng/toast';
import {DataViewModule} from 'primeng/dataview';
import {ImageModule} from 'primeng/image';
import { SignService } from '../../services/SignService';
import { CityService } from '../../services/CityService';
import { TableModule } from 'primeng/table';
import {InputTextModule} from 'primeng/inputtext';
@NgModule({
  imports: [FormsModule,InputTextModule,
    CommonModule,MessagesModule,MessagesModule,DataViewModule,ImageModule,
    ReactiveFormsModule,FileUploadModule,ToastModule,MessageModule,TableModule,
    SharedModule,
    RootRoutingModule
  ],
  declarations: [
    HomePageComponent,
    MainCityBoardComponent,
    HeaderComponent,
    SearchBarComponent,
    FooterComponent
  ],
  exports: [
    HomePageComponent,
    MainCityBoardComponent,
    HeaderComponent,
    SearchBarComponent,
    FooterComponent
  ],
  providers: [
    
    SignService,CityService
  ]
})

export class RootModule {
}
