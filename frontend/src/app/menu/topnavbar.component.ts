import {Component, Injectable, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";
import {HomeComponent} from "../home/home.component";
import {Http, Headers, Response, RequestOptionsArgs, RequestOptions} from '@angular/http';
import {LoginService} from "../auth/login/login.service";
import {Router} from "@angular/router";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {User} from "../models/user-model";

@Component({
  selector: "top-navbar",
  templateUrl: "./topnavbar.component.html"
})

@Injectable()
export class TopnavbarComponent implements OnInit {

  public images = GlobalVariable.PATH_IMAGES;

  constructor(public homeComponent: HomeComponent,
              public loginService: LoginService,
              public http: Http,
              public router: Router) {

  }

  ngOnInit(): void {

  }

  menuToggle() {
    this.homeComponent.changeMenuStyle();
  }

  public logout() {
    this.loginService.logout();
    this.router.navigateByUrl("login");
  }
}
