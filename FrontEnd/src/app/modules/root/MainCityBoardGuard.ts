import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

import { RoutesConfig } from '../../configs/routes.config';
import { SignService } from '../../services/SignService';

@Injectable()
export class MainCityBoardGuard implements CanActivate {
  constructor(private signService: SignService,
              private router: Router) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Promise<boolean> {
    return new Promise(resolve => {
      if (this.signService.isLoggedIn()) {
        resolve(true);
      } else {
        this.router.navigate([RoutesConfig.routes.home]);
        resolve(false);
      }
    });
  }
}
