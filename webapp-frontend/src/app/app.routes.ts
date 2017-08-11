import {LoginComponent} from './login/login.component';
import {HomeComponent} from "./home/home.component";

export const appRoutes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
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
