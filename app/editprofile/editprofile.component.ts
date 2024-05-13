import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MovieService } from '../services/movie.service';
import { MatDialog } from '@angular/material/dialog';
import { NgToastService } from 'ng-angular-popup';
import { User } from '../model/user';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit{
  registration:FormGroup = new FormGroup({
   
    userName : new FormControl('', [Validators.required, Validators.pattern("[A-Za-z\s]*$")]),
    mobileNo: new FormControl("", [Validators.required, Validators.pattern("[1-9][0-9]{9}")])


  })


  get userName() {return this.registration.get("userName")} 
  get mobileNo() {return this.registration.get("phoneNo")}
 


  constructor(private movieService:MovieService,public dialog: MatDialog, private toast :NgToastService){}
  ngOnInit(): void {
    this.movieService.getUserData().subscribe(data=>{ 
      this.user=data;
     })
  }

  user?:User={};

  email:any ;
  update(){
    this.email=localStorage.getItem("email") ;
    console.log(this.email);
    this.movieService.updateUserData(this.registration.value).subscribe(response=>{
      console.log(response);
      this.toast.success({detail:"Updated Successfully ✅",duration:5000})
      this.dialog.closeAll();
    }) ;
  }

}
