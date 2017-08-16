import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router, ActivatedRouteSnapshot, RouterState, RouterStateSnapshot} from '@angular/router';
import {LoginService} from "./login.service";
import {Observable} from "rxjs/Observable";
import {User} from '../../models/user-model';
import {NgbAlert} from "@ng-bootstrap/ng-bootstrap";
import * as GlobalVariable from "../../globals";


@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  public user: User = new User();
  public btn_login = "Login";
  public hasMessage = false;
  public failCount = 0;
  public captchaSrc = GlobalVariable.BASE_API_URL + "captcha";
  public ngbAlert = {
    type: 'warning',
    dismissible: false,
    message: 'Username or password cannot be empty.'
  };

  constructor(public router: Router,
              public loginService: LoginService) {

  }

  ngOnInit(): void {

  }

  public refreshCaptcha() {
    this.captchaSrc = GlobalVariable.BASE_API_URL + "captcha" + "?v=" + Date.now();
  }

  public login(form) {
    if (form.valid) {
      this.loginService.login(this.user);
      if (this.loginService.getCurrentUser().subscribe(
          data => {
            this.failCount = 0;
            this.router.navigateByUrl("home");
          },
          error => {
            this.failCount ++;
            this.ngbAlert.message = error;
            this.hasMessage = true;
          }
        )) {
      }
    } else {
      this.hasMessage = true;
    }
  }

}
