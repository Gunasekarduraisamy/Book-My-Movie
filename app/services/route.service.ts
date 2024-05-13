import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RouteService {
  navigateByUrl(newUrl: string) {
    throw new Error('Method not implemented.');
  }
  
  constructor(private router:Router) { }

  navigateToHomeView() {
    this.router.navigate([""]);
  }
  navigateToLoginView() {
    this.router.navigate(["signin"]);
  }

  navigateToMovieCart(){
    this.router.navigate(["movie-card/:id"])
  }

  navigateToFavourite(){
    this.router.navigate(["favmovies"])
  }
}
