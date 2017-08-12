import {LoginComponent} from './auth/login/login.component';
import {HomeComponent} from "./home/home.component";

export const appRoutes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: './home/home.module#HomeModule'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '**', // fallback router must in the last
    component: LoginComponent
  }
];
