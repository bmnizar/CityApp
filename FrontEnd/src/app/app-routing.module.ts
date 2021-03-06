import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoutesConfig } from './configs/routes.config';

const routes: Routes = [
  { path: RoutesConfig.basePaths.auth, loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule) },
   { path: '**', redirectTo: RoutesConfig.routes.maincityboard}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      initialNavigation: 'enabled',
      scrollPositionRestoration: 'enabled',
      anchorScrolling: 'enabled',
      relativeLinkResolution: 'legacy'
    })
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule {
}
