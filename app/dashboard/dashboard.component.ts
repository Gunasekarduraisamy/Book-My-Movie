import { Component, Input, OnInit } from '@angular/core';
import { Movie } from '../model/movie';
import { MoviesDataService } from '../services/movies-data.service';
import { AuthenticationService } from '../services/authentication.service';
import { RouteService } from '../services/route.service';
import { NgToastService } from 'ng-angular-popup';
import { FavouriteComponent } from '../favourite/favourite.component';
 
 

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @Input()
  Movies:Array<Movie>=[];

  Tamil: string = "Tamil";
  Telugu: string = "Telugu";
  English: string = "English";
  Hyderabad:string="Hyderabad";
  Chennai:string="Chennai";
  all: string = "All"

  isLoggedIn=false;
  constructor(private  movies:MoviesDataService,private authService:AuthenticationService,
    private router:RouteService,private toast :NgToastService ){}
  
  // ngOnInit():void{
  //   this.movies.getAllMovies().subscribe({
  //     next:(data:Array<Movie>)=>{
  //       this.Movies=data;
  //     },
  //     error:err=>{
  //       alert(err);
  //     }
  //   });
  // }

  ngOnInit():void{
    this.isLoggedIn=this.authService.isLogin() ;
    this.movies.getAllMovies().subscribe({
      next:(data:Array<Movie>)=>{
        this.Movies=data;
      },
      error:err=>{
        alert(err);
      }
    });
  }


  onSearch(searchText: string) {
    if (searchText === '') {
      this.movies = this.movies;
    }
    this.movies.getAllMovies().subscribe((data:any) => {
      this.Movies = data.filter(movie => movie.movieName?.toLowerCase().includes(searchText.toLowerCase()))
    })
  }

  onfilter() {
    this.movies.getAllMovies().subscribe({
      next: data => {
        this.Movies = data;
      },
      error: err => {
        alert(err);
      }
    });
  }
  items(name: string) {
    this.movies.getAllMovies().subscribe({
      next: (data) => {
        if (name || name !== ' ') {
          this.Movies = data.filter((product) =>product.movieLanguage?.includes(name)
          );
        } else {
          this.Movies= data;
        }
      },
      error: (error) => {
        alert('Something Error !! Please Try Again Later');
      },
    });
  }

  item(name: string) {
    this.movies.getAllMovies().subscribe({
      next: (data) => {
        if (name || name !== ' ') {
          this.Movies = data.filter((product) =>product.theaterCity?.includes(name)
          );
        } else {
          this.Movies= data;
        }
      },
      error: (error) => {
        alert('Something Error !! Please Try Again Later');
      },
    });
  }
  callFavComp(){
    if(this.isLoggedIn){
       this.router.navigateToFavourite();
     
    }
    
    else{
      this.toast.warning({detail:"Need to SignIn ⚠️",summary:"SignIn to get access for favourte Movies!!",duration:5000})
     }
   
  }

}




