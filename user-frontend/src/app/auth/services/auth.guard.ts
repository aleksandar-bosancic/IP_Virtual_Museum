import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanDeactivate,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private service: AuthService) {
  }
  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): boolean | UrlTree {
    if (this.service.loggedIn){
      if (route.routeConfig?.path === 'register') {
        console.log("registered")
        return this.router.parseUrl('/');
      } else {
        return true;
      }
    } else {
      if (route.routeConfig?.path === 'register') {
        return true;
      } else {
        return this.router.parseUrl('/');
      }
    }
    return this.service.loggedIn;
  }
}

//TODO: Napravi novi guard da zabrani login i register ako si logovan
