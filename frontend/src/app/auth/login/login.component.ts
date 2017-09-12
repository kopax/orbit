import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {LoginService} from "./login.service";
import {User} from '../../models/user-model';
import {Subject} from "rxjs/Subject";
import {Token} from "../../models/token-model";
import * as GlobalVariable from "../../globals";


@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  public user: Token = new Token();
  public hasMessage = false;
  public failCount = 0;
  public captchaKey = Date.now();
  public captchaSrc = "";
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
    this.captchaKey = Date.now();
    this.captchaSrc = GlobalVariable.BASE_API_URL + "captcha?v=" + this.captchaKey;
  }

  public login(form) {
    if (form.valid) {
      this.user.captchaKey = this.captchaKey;
      this.loginService.login(this.user);
      if (this.loginService.getCurrentUser().subscribe(
          data => {
            this.failCount = 1;
            this.router.navigateByUrl("home");
          },
          error => {
            this.failCount++;
            this.ngbAlert.message = error;
            this.hasMessage = true;
            this.loginService.subject = new Subject<User>();
            if (this.failCount >= 3) {
              this.refreshCaptcha();
            }
          }
        )) {
      }
    } else {
      if (this.failCount >= 3) {
        this.ngbAlert.message = "Username、password and captcha cannot be empty"
      } else {
        this.ngbAlert.message = "Username or password cannot be empty";
      }
      this.hasMessage = true;
    }
  }

}
