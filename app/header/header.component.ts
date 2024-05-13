import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { RouteService } from '../services/route.service';
import { TokenInterceptorService } from '../services/token-interceptor.service';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { NgToastService } from 'ng-angular-popup';
import { ViewprofileComponent } from '../viewprofile/viewprofile.component';
import { EditprofileComponent } from '../editprofile/editprofile.component';
import { Movie } from '../model/movie';
import { MoviesDataService } from '../services/movies-data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  Movies:Array<Movie>=[];
  isLoggedIn=false;
  constructor(private movies:MoviesDataService,private authService:AuthenticationService, private router:RouteService, private tokenService:TokenInterceptorService,public dialog: MatDialog,
    private toast :NgToastService){}
    

  openDialog1() {
    const dialogRef = this.dialog.open(LoginComponent);
    dialogRef.afterClosed().subscribe(result => {
    });
  }
  name:any ;
  ngOnInit(){

    this.isLoggedIn=this.authService.isLogin() ;
    this.name=this.tokenService.getUserName()?.toUpperCase() ;
  }

  signOut(){
    this.authService.isLogOut();
    this.router.navigateToHomeView();
    location.reload();
  }

  viewProfile(){
    this.dialog.open(ViewprofileComponent) ;
    
  }
  editProfile(){
    this.dialog.open(EditprofileComponent) ;
  }
  


  onSearch(searchText: string) {
    if (searchText === '') {
      this.movies = this.movies;
    }
    this.movies.getAllMovies().subscribe((data:any) => {
      this.Movies = data.filter(movie => movie.movieName?.toLowerCase().includes(searchText.toLowerCase()))
    })
  }

}
