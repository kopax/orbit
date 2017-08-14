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

  public getCurrentUser(): Observable<User> {
    Observable.create()
    return JSON.parse(localStorage.getItem(GlobalVariable.currentUser));
  }

  public login(user: User) {
    return this.http
      .post(this.loginURL, user)
      .map(response => response.json())
      .subscribe(
        data =>  {
          if (data && data.token) {
            localStorage.setItem(GlobalVariable.currentUser, JSON.stringify(data));
          }
        },
        error => {
          console.log(error);
        }
      )
  }

}
