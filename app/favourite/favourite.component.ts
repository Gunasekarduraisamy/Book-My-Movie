import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { NgToastService } from 'ng-angular-popup';
import { AuthenticationService } from '../services/authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  
  ngOnInit(): void {
    this.isLoggedIn=this.authService.isLogin() ;
    this.movieService.getFavouriteListMovie().subscribe(response=>{
      this.favouriteMovieList=response;
      console.log(this.favouriteMovieList);
     })
   
  }
  isLoggedIn=false;
  favMovie:any;

  constructor(private movieService:MovieService,private toast :NgToastService,private authService:AuthenticationService,
    private snackBar:MatSnackBar,private routeServices:RouteService  ){}

  favouriteMovieList:any ;

  deleteMovieFromFavouriteList(movieId){
    console.log(movieId);
    this.movieService.deleteFavouriteListMovie(movieId).subscribe(responce=>{
      if(!responce){
        alert("failed to connect to server");
      }
      else{
        this.snackBar.open("Movie Delete To Your FavouriteList !!","",{
          duration:5000
        })
        location.reload();
      }
    })
  }

   
}
