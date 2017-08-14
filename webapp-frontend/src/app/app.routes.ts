import {LoginComponent} from './auth/login/login.component';
import {HomeComponent} from "./home/home.component";

export const appRoutes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    loadChildren: './home/home.module#HomeModule'
  },
  {
    path: '**', // fallback router must in the last
    component: LoginComponent
  }
];
