import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { MovieAddComponent } from './movie-add/movie-add.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { MovieCartComponent } from './movie-cart/movie-cart.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { CanDeactivateGuard } from './can-deactivate.guard';


const routes: Routes = [
  {path:"",component:HomeComponent},
  {path:"signin",component:LoginComponent},
  {path:"signup",component:RegisterComponent},
   {path:"movie-add",component:MovieAddComponent},
   {path:"movie-card/:id",component:MovieCartComponent,canDeactivate: [CanDeactivateGuard]},
   {path:"favmovies",component:FavouriteComponent},
   {path:"**",component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
