import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { MovieService } from '../services/movie.service';
import {MatDialog} from '@angular/material/dialog'
import { NgToastService } from 'ng-angular-popup';
import { MatSnackBar } from '@angular/material/snack-bar';
 

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registration:UntypedFormGroup = new UntypedFormGroup({
   
    
     userEmail: new UntypedFormControl('', [  Validators.required,Validators.pattern("[a-z0-9]+@[a-z]+\.[a-z]{2,3}")]),
    userName: new UntypedFormControl('', [Validators.required, Validators.pattern("^[A-Za-z\s]+$")]),
    password: new UntypedFormControl('',[Validators.required,Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$")]),
    cpassword: new UntypedFormControl('',[Validators.required,Validators.minLength(9),Validators.maxLength(10)]),
    mobileNo: new UntypedFormControl("", [ Validators.required,Validators.pattern("[6-9][0-9]{9}")])
  })
  get userEmail() {return this.registration.get("userEmail")}
  get password() {return this.registration.get("password")}
  get cpassword() {return this.registration.get("cpassword")}
  get userName() {return this.registration.get("userName")} 
  get mobileNo() {return this.registration.get("mobileNo")}

  constructor(private service:MovieService, private route : Router,   public dialog: MatDialog ,private toast :NgToastService ,private snackBar: MatSnackBar) { }

  submit() 
  { 
    const data=this.registration.value
    this.toast.success({detail:"REGISTERED😊",summary:"Registered mail 📧 sent successfully !!",duration:5000}) ;
    console.log(data);
    this.dialog.closeAll();
    this.service.registerUserData(data).subscribe(res=>{
      console.log(res);
      this. route.navigateByUrl("") ;
      
    }
    )
    
  }

  ngOnInit(): void { }


}
