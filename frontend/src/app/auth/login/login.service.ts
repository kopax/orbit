import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../../models/user-model';
import {Subject} from "rxjs/Subject";
import {Token} from "../../models/token-model";
import {HttpClient} from "@angular/common/http";
import * as GlobalVariable from "../../globals";

@Injectable()
export class LoginService {

  public loginURL = GlobalVariable.BASE_API_URL + 'login';

  constructor(public http: HttpClient) {
  }

  public login(token: Token): Promise<User> {
    return this.http
      .post(this.loginURL, token)
      .toPromise()
      .then(user => {
        localStorage.setItem(GlobalVariable.CURRENT_USER, JSON.stringify(user));
        return user;
      }).catch(reason => Promise.reject(reason));
    // .subscribe(
    //   user => {
    //     if (user && user.token) {
    //       localStorage.setItem(GlobalVariable.CURRENT_USER, JSON.stringify(user));
    //       this.subject.next(Object.assign({}, user));
    //     }
    //   },
    //   error => {
    //     this.subject.error((error.error && JSON.parse(error.error).message) || "网络连接超时");
    //   }
    //)
  }

  public logout(): void {
    localStorage.removeItem(GlobalVariable.CURRENT_USER);
  }

}
