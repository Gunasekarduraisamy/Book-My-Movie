import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Movie } from '../model/movie';

@Injectable({
  providedIn: 'root'
})
export class MoviesDataService {

   url:string="http://localhost:9000/api/v3/save";
   url1:string="http://localhost:9000/api/v3/movies";
   url2:string="http://localhost:9000/api/v3/delete";
  
  constructor(private http:HttpClient) { }

  saveMovie(data:any):Observable<Movie>{
    
    return this.http.post(`${this.url}`,data);
  }

  getAllMovies():Observable<Array<Movie>>{
   return this.http.get<Array<Movie>>(`${this.url1}`);
  }

  deleteMovie(id:any){
    return this.http.delete(`${this.url2}`);
  }

  getMovieById(id:any){
    return this.http.get(`${this.url1}/${id}`);
  }
}
