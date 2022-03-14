import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
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
        return this.router.parseUrl('/');
      } else {
        return true;
      }
    } else {
      if (route.routeConfig?.path === 'register') {
        return true;
      } else {
        alert('Please log in or register');
        return this.router.parseUrl('/');
      }
    }
  }
}
