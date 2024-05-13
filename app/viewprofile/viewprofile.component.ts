import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { MovieService } from '../services/movie.service';
import { TokenInterceptorService } from '../services/token-interceptor.service';
import { User } from '../model/user';

@Component({
  selector: 'app-viewprofile',
  templateUrl: './viewprofile.component.html',
  styleUrls: ['./viewprofile.component.css']
})
export class ViewprofileComponent implements OnInit {

  constructor(private movieService:MovieService,private router:Router,public dialog: MatDialog,private tokenservice:TokenInterceptorService
    ) { }
  
    email=localStorage.getItem("email") ;
    // userName:any ;
    // password: any ;
    // phoneNo:any ;
    
    user?:User={};
  ngOnInit(): void {
    this.email=this.tokenservice.getUserEmail();
    
     this.movieService.getUserData().subscribe(data=>{ 
      this.user=data;
     })
  }
  ok(){
    this.dialog.closeAll();
  }

}
