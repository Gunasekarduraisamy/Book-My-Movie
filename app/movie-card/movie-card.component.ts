import { Component, Input, OnInit } from '@angular/core';
import { Movie } from '../model/movie';
import { AuthenticationService } from '../services/authentication.service';
import { ActivatedRoute } from '@angular/router';
import { MoviesDataService } from '../services/movies-data.service';
import { Movies } from '../model/movies';
import { MovieService } from '../services/movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouteService } from '../services/route.service';
import { Favourite } from '../model/favourite';
import { NgToastService } from 'ng-angular-popup';

@Component({
  selector: 'app-movie-card',
  templateUrl: './movie-card.component.html',
  styleUrls: ['./movie-card.component.css']
})
export class MovieCardComponent implements OnInit {
@Input() movie?:Movie;
movies?:Movies={};
favourite?:Favourite={}
submitStatus:boolean=false;
isLoggedIn=false;
constructor(private authService:AuthenticationService,private activatedRoute: ActivatedRoute,private moviesData:MoviesDataService,private movieService:MovieService,
  private snackBar:MatSnackBar,private routeServices:RouteService,private toast :NgToastService){}

ngOnInit(){

  this.isLoggedIn=this.authService.isLogin() ;

}

favMovie:any;
searchMovieBasedOnId(movieId){
  console.log(movieId);
  this.moviesData.getAllMovies().subscribe(data=>{
  this.favMovie=data.filter(x=>x.movieId==movieId);
  console.log(this.favMovie);
  this.addFavourite(this.favMovie[0])
  })
   
 

}

addFavourite(movie:any){
  console.log(movie);
  this.movieService.saveFavouriteMovie(movie).subscribe(response=>{
    if(!response){
      this.toast.warning({detail:"Movie Not Added",summary:"Movie Already Exist in Your FavouriteList !!",duration:5000})
      alert("failed to connect to server");
    }
    else{
      this.snackBar.open("Movie Added To Your FavouriteList !!","",{
        duration:5000
      })
      this.toast.success({detail:"MovieAdded✅",summary:"Movie Added To Your FavouriteList !!",duration:5000})
    }
  })
}
 

sound(){
  let audio= new Audio();
  audio.src="./assets/sound.mp3";
  audio.load();
  audio.play();
  }
}

