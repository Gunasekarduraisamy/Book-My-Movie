import { Component, Input, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
 
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
 
import { SessionLogService } from '../services/session-log.service';
import { TokenInterceptorService } from '../services/token-interceptor.service';
import { Movie } from '../model/movie';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginform=new FormGroup({
    // userEmail:new FormControl('',[Validators.required, Validators.pattern("[a-z0-9]+@[a-z]+\.[a-z]{2,3}")]),
    userEmail : new FormControl('', [Validators.required, Validators.pattern("[a-z0-9]+@[a-z]+\.[a-z]{2,3}")]),
    password: new FormControl("",[Validators.required,Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$")]),
   })
   get userEmail() {return this.loginform.get("userEmail")}
   get password() {return this.loginform.get("password")}

   hide=true;
   loginMsg: boolean = false;
   @Input() movie?:Movie;
  constructor(private authenticationService:AuthenticationService,  private   sessionLogService: SessionLogService   ,private router :Router,public dialog: MatDialog,private tokenservice:TokenInterceptorService) { }
  ngOnInit(): void {
     
    }
  


   
  login(){
    const userDetails = {
      userEmail: this.loginform.value.userEmail,
      password: this.loginform.value.password
    }
    console.log(userDetails.userEmail+" hiii ")
    this.authenticationService.loginUser(userDetails).subscribe({
      next: data => {
        console.log(data);
        if (data) {
          localStorage.setItem('token', data);
          this.sessionLogService.login();
       const email= this.tokenservice.getUserEmail()
        if(email==='admin@gmail.com'){
          this.router.navigateByUrl("/movie-add");
        }
        else
        {
          this.router.navigateByUrl("/");
        }
       
           
        }
      },
      error: err => {
        this.loginMsg = true;
      }
    });
  }

 

}
