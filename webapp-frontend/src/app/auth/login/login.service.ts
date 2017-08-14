import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Http, Headers, Response} from '@angular/http';
import {User} from '../../models/user-model';
import 'rxjs/add/operator/map';
import * as GlobalVariable from "../../globals";

@Injectable()
export class LoginService {
  public loginURL = GlobalVariable.baseApiUrl + 'login';

  constructor(public http: Http) {
  }

  public get currentUser(): Observable<User> {
    return null;
  }

  public login(user: User) {
    return this.http
      .post(this.loginURL, user)
      .map(response => {
        const user = response.json();
        console.log('user object > ' + user);
        if (user && user.token) {
          localStorage.setItem("currentUser", JSON.stringify(user));
        }
      })
      .subscribe(
        data =>  {
          console.log("login => success" + data);
        },
        error => {
          console.log(error);
        }
      )
  }

}
