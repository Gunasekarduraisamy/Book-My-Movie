import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
 

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
   
  constructor(private http:HttpClient) { }
  saveUser(userDetails: any) {
    return this.http.post(`http://localhost:9000/api/v1/save `, userDetails, {
      observe: 'response', withCredentials: true  // withCredentials: true => mean browser can stored data inside the cookies related to thise request
    });
  }

  loginUser(user: any) {
    return this.http.post(`http://localhost:9000/api/v1/login`, user, { responseType: 'text', withCredentials: true });
  }
   
  isLogin(){
    
    let token=localStorage.getItem("token");
    if(token==undefined || token=='' || token==null){
      return false;
    }else{
      return true;
    }
  }

  isLogOut(){
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    return  true;
  }
    
}
