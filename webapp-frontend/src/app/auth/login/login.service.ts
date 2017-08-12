import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';
import {Jsonp, Headers, Response} from '@angular/http';
import {User} from '../../models/user-model';
import 'rxjs/add/operator/map';
import * as GlobalVariable from "../../globals";

@Injectable()
export class LoginService {
  public loginURL = GlobalVariable.baseApiUrl + 'login';
  public subject: Subject<User> = new Subject<User>();

  constructor(public jsonp: Jsonp) {
  }

  public get currentUser(): Observable<User> {
    return this.subject.asObservable();
  }

  public login(user: User) {
    return this.jsonp
      .get(this.loginURL + "?username=" + user.username + "&password=" + user.password)
      .map((response: Response) => {
        const user = response.json();
        console.log('user object > ' + user);
        if (user && user.token) {
          localStorage.setItem("currentUser", JSON.stringify(user));
          this.subject.next(Object.assign({}, user));
        }
        return response;
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
