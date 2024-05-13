import { Component, OnInit } from '@angular/core';
import { Movie } from '../model/movie';
import { MovieService } from '../services/movie.service';
import { FormControl, FormGroup, UntypedFormBuilder, UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { TokenInterceptorService } from '../services/token-interceptor.service';
import { Route, Router } from '@angular/router';
import { MoviesDataService } from '../services/movies-data.service';

@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit{
movie:Movie={}

  // movieForm: FormGroup;
registration:FormGroup=new FormGroup({
 movieId:new FormControl('',[Validators.required,Validators.minLength(5)]),
 image:new FormControl('',[Validators.required]),
movieName:new FormControl('',[Validators.required]),
movieLanguage:new FormControl('',[Validators.required]),
theaterId:new FormControl('',[Validators.required]),
theaterName:new FormControl('',[Validators.required]),
theaterCity:new FormControl('',[Validators.required]),

})
get movieId(){return this.registration.get("movieId")}
get image(){return this.registration.get("iamge")}
get movieName(){return this.registration.get("movieName")}
get movieLanguage(){return this.registration.get("movieLanguage")}
get theaterId(){return this.registration.get("theaterId")}
get theaterName(){return this.registration.get("theaterName")}
get theaterCity(){return this.registration.get("theaterCity")}

ngOnInit(): void {
  
}
  constructor(private movieService: MovieService,private tokenservice:TokenInterceptorService,private router:Router,private movies:MoviesDataService) { }



  
  submit(){
    const data=this.registration.value;
    console.log(data);
    this.movies.saveMovie(data).subscribe(
      res=>{
        console.log(res);
        this.router.navigateByUrl("/");
      }
    )
  }
   

}
