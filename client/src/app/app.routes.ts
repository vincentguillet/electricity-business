import { Routes } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {RegisterComponent} from './pages/auth/register/register.component';
import {guestOnlyCanActivate, guestOnlyCanMatch} from './guards/guest-only.guard';
import {LoginComponent} from './pages/auth/login/login.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {authOnlyCanActivate, authOnlyCanMatch} from './guards/auth-only.guard';
import {DashboardComponent} from './pages/dashboard/dashboard.component';

export const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: HomeComponent
      },
      {
        path: 'home',
        component: HomeComponent
      }
    ]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canMatch: [guestOnlyCanMatch],
    canActivate: [guestOnlyCanActivate]
  },
  {
    path: 'login',
    component: LoginComponent,
    canMatch: [guestOnlyCanMatch],
    canActivate: [guestOnlyCanActivate]

  },
  {
    path: 'profile',
    component: ProfileComponent,
    canMatch: [authOnlyCanMatch],
    canActivate: [authOnlyCanActivate]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canMatch: [authOnlyCanMatch],
    canActivate: [authOnlyCanActivate]
  },
  {
    path: '**',
    redirectTo: ''
  }
];
