import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Http, Headers, Response} from '@angular/http';
import {User} from '../../models/user-model';
import {Subject} from "rxjs/Subject";
import 'rxjs/add/operator/map';
import * as GlobalVariable from "../../globals";
import {Token} from "../../models/token-model";

@Injectable()
export class LoginService {
  public loginURL = GlobalVariable.BASE_API_URL + 'login';
  public subject: Subject<User> = new Subject<User>();

  constructor(public http: Http) {
  }

  public getCurrentUser(): Observable<User> {
    return this.subject.asObservable();
  }

  public login(user: Token) {
    return this.http
      .post(this.loginURL, user)
      .map(response => response.json())
      .subscribe(
        response => {
            const data = response.data;
            if (data && data.token) {
              localStorage.setItem(GlobalVariable.CURRENT_USER, JSON.stringify(data));
              this.subject.next(Object.assign({}, data));
            }
        },
        error => {
          this.subject.error((error._body && JSON.parse(error._body).message) || "网络连接超时");
        }
      )
  }

  public logout(): void {
    localStorage.removeItem(GlobalVariable.CURRENT_USER);
    this.subject.next(Object.assign({}));
  }

}
