import { InjectionToken } from '@angular/core';

export const ROUTES_CONFIG = new InjectionToken('routes.config');

const basePaths = {
  hero: 'hero',
  auth: 'auth',
};

const routesNames = {
  home: '',
  maincityboard: 'maincityboard',
  hero: {
    myHeroes: 'my-heroes',
    detail: ':id',
  },
  auth: {
    signUp: 'sign-up',
    logIn: 'log-in',
  }
};

export const getHeroDetail = (id: string) => `/${basePaths.hero}/${id}`;

export const RoutesConfig: any = {
  basePaths,
  routesNames,
  routes: {
    home: `/${routesNames.home}`,
    maincityboard: `/${routesNames.maincityboard}`,
    hero: {
      myHeroes: `/${basePaths.hero}/${routesNames.hero.myHeroes}`,
      detail: getHeroDetail
    },
    auth: {
      signUp: `/${basePaths.auth}/${routesNames.auth.signUp}`,
      logIn: `/${basePaths.auth}/${routesNames.auth.logIn}`,
    }
  }
};


