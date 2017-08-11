import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router, ActivatedRouteSnapshot, RouterState, RouterStateSnapshot} from '@angular/router';
import {LoginService} from "./login.service";
import {Observable} from "rxjs/Observable";
import {User} from '../models/user-model';


@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public user: User = new User();
  public error: Error;

  btn_login = "Login";

  constructor(public router: Router,
              public activatedRoute: ActivatedRoute,
              public loginService: LoginService) {

  }

  ngOnInit(): void {

  }

  public login() {
    this.loginService.login(this.user);
    this.router.navigateByUrl("home");
  }

}