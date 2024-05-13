import {Injectable} from'@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MovieCartComponent } from './movie-cart/movie-cart.component';

@Injectable({
    providedIn: 'root'
  })
  export class CanDeactivateGuard implements CanDeactivate<unknown> {
    canDeactivate(
      component: MovieCartComponent,
      currentRoute: ActivatedRouteSnapshot,
      currentState: RouterStateSnapshot,
      nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return component.canDeactivate ? component.canDeactivate() : true;
  }
    }

