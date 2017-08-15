import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router, ActivatedRouteSnapshot, RouterState, RouterStateSnapshot} from '@angular/router';
import {LoginService} from "./login.service";
import {Observable} from "rxjs/Observable";
import {User} from '../../models/user-model';
import {NgbAlert} from "@ng-bootstrap/ng-bootstrap";


@Component({
  selector: 'login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  public user: User = new User();
  public btn_login = "Login";
  public hasMessage = false;
  public failCount = 0;
  public ngbAlert = {
    type: 'danger',
    dismissible: false,
    message: 'Username or password cannot be empty.'
  };

  constructor(public router: Router,
              public loginService: LoginService) {

  }

  ngOnInit(): void {

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
            console.log(error);
          }
        )) {
      }
    } else {
      this.hasMessage = true;
    }
  }

}
