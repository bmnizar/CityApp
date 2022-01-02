import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { RoutesConfig } from '../../configs/routes.config';
import { MainCityBoardComponent } from './pages/main-city-board/main-city-board';
import { MainCityBoardGuard } from './MainCityBoardGuard';

const routesNames = RoutesConfig.routesNames;

const rootRoutes: Routes = [
  { path: routesNames.home, component: HomePageComponent, pathMatch: 'full' },
  { path: routesNames.maincityboard, component: MainCityBoardComponent, canActivate: [MainCityBoardGuard] }
];

@NgModule({
  imports: [
    RouterModule.forChild(rootRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: [
    
    MainCityBoardGuard
  ]
})

export class RootRoutingModule {
}
