import { Component, OnInit } from '@angular/core';
import { Movie } from '../model/movie';
import { MoviesDataService } from '../services/movies-data.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { Movies } from '../model/movies';
import { MovieService } from '../services/movie.service';
import { RouteService } from '../services/route.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthenticationService } from '../services/authentication.service';
 

declare var Razorpay: any;
@Component({
  selector: 'app-movie-cart',
  templateUrl: './movie-cart.component.html',
  styleUrls: ['./movie-cart.component.css']
})
export class MovieCartComponent implements OnInit{
ngOnInit(): void {
  this.activatedRoute.paramMap.subscribe(param => {
    let id = param.get("id") ?? 0;
    console.log(id);
  this.moviesData.getMovieById(id).subscribe(data=>{
    this.movie=data;
    this.submitStatus=false;
  })
   
})
}
 constructor (private moviesData:MoviesDataService, private activatedRoute: ActivatedRoute, private movieService:MovieService,private routeServices:RouteService
  ,private snackBar:MatSnackBar,private authService:AuthenticationService){}

movie?:Movie={};
movies?:Movies={};
submitStatus:boolean=false;
SeatType =[
  { name: 'Silver', price: 450 },
  { name: 'Gold', price: 500 },
  { name: 'Platinum', price: 550 },
];

signOut(){
  this.authService.isLogOut();
  this.routeServices.navigateToHomeView();
  // location.reload();
}

canDeactivate() {
  if (!this.submitStatus)
    this.submitStatus = confirm("You have not submitted a request to this Book Tickets. Any details entered will be lost. Are you sure you want to leave?");
  return this.submitStatus;
}

bookTicket(){
  if(this.movies.seatType){
    this.movies.movieId=this.movie?.movieId;
    this.movies.movieName=this.movie?.movieName;
    this.movies.movieLanguage=this.movie?.movieLanguage;
    const totalAmount = this.calculateBillAmount();
    const RazorpayOptions = {
      description: "Online-Movie-Booking",
      currency:"INR",
      amount: (totalAmount*100),
      name: "Hari",
      Key:"rzp_test_JYFGYHFUZHeAyy",
      // image:"https://i.imgur.com/FApqk3D.jpeg",
      prefill: {
        name: "Hari Rajan",
        email: "hari@gmail.com",
        phone: "9944296921"
      },
      theme: {
        color: "#f37254"
      },
      modal: {
        ondismiss: () =>{
          console.log('dismissed')
        }
      }
    }
    const successCallback = (paymentid: any) => {
      console.log(paymentid);
    }

    const failureCallback = (e: any) => {
      console.log(e);
    }
    Razorpay.open(RazorpayOptions, successCallback,failureCallback)
    this.movieService.saveMovie(this.movies).subscribe({
      next:data=>{
        this.snackBar.open("Request is Submitted and your Movie Booking will placed","",{
          duration:4000
      });
      this.submitStatus=true;
            this.routeServices.navigateToHomeView();
    },
    error:err=>{
      alert("failed to connect to server");
    }
   })

  }
}
 

calculateBillAmount():number{
let total:number=0;
  if (
    this.movies.seatType &&
    this.movies.quantity &&
    (this.movies.quantity) > 0
  ) { 
    const seatPrice = this.movies.seatType['price'];
     
    const finalPrice = Number (seatPrice);
     total= finalPrice * (this.movies.quantity);
    return  total;
  } 
  else {
    return 0; 
  }
}

}
