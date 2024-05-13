import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
 
  url:string="http://localhost:9000/api/v2/register"
  
  constructor(private http:HttpClient) { }
  registerUserData(data:any)
  {
    console.log(data);
    return this.http.post(this.url,data)
  }

  saveMovie(data:any)
  {
    let httpHeader = new HttpHeaders({
      'Authorization':'Bearer ' +localStorage.getItem("token")
    });
    console.log(localStorage.getItem("token"));
    let requestOption = {headers:httpHeader}
    return this.http.post(`http://localhost:9000/api/v2/user/movie`,data,requestOption)
  }


  getAllMovies(email:any)
  {
    return this.http.get(`http://localhost:9000/api/v2/user/movies/${email}`)
  }

  deleteMovie(email:any,id:any)
  {
    return this.http.delete(`http://localhost:9000/api/v2/user/movie/${email}/${id}`)
  }

  updateMovie(email:any,data:any)
  {
    return this.http.put(`http://localhost:9000/api/v2/user/movies/${email}`,data)
  }

  getUserData(){
    let httpHeader = new HttpHeaders({
      'Authorization':'Bearer ' +localStorage.getItem("token")
    });
    console.log(localStorage.getItem("token"));
    let requestOption = {headers:httpHeader}
    return this.http.get(`http://localhost:9000/api/v2/user/getuser`,requestOption)
  }

  updateUserData(data:any){
    let httpHeader = new HttpHeaders({
      'Authorization':'Bearer ' +localStorage.getItem("token")
    });
    console.log(localStorage.getItem("token"));
    let requestOption = {headers:httpHeader}
    return this.http.put(`http://localhost:9000/api/v2/user/updateuser`,data,requestOption)
  }
  saveFavouriteMovie(data:any){
    console.log(data);
    let httpHeader = new HttpHeaders({
      'Authorization':'Bearer ' +localStorage.getItem("token")
    });
    console.log(localStorage.getItem("token"));
    let requestOption = {headers:httpHeader}
    return this.http.post(`http://localhost:9000/api/v2/user/favourite`,data,requestOption)
  }

  getFavouriteListMovie(){
    let httpHeader = new HttpHeaders({
      'Authorization':'Bearer ' +localStorage.getItem("token")
    });
    console.log(localStorage.getItem("token"));
    let requestOption = {headers:httpHeader}
    return this.http.get(`http://localhost:9000/api/v2/user/favourites`,requestOption)
  }

  deleteFavouriteListMovie(id:any){
    console.log(id);
    let httpHeader = new HttpHeaders({
      'Authorization':'Bearer ' +localStorage.getItem("token")
    });
    console.log(localStorage.getItem("token"));
    let requestOption = {headers:httpHeader}
    return this.http.delete(`http://localhost:9000/api/v2/user/favourite/${id}`,requestOption)
  }
}


