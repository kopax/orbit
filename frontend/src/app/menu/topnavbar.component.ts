import {Component, Injectable, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";
import {HomeComponent} from "../home/home.component";
import {Http, Headers, Response} from '@angular/http';
import {LoginService} from "../auth/login/login.service";
import {Router} from "@angular/router";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Component({
  selector: "top-navbar",
  templateUrl: "./topnavbar.component.html"
})

@Injectable()
export class TopnavbarComponent implements OnInit {

  public images = GlobalVariable.PATH_IMAGES;
  public isShowMessages: boolean = false;
  public isShowProfile: boolean = false;

  constructor(public homeComponent: HomeComponent,
              public loginService: LoginService,
              public http: Http,
              public router: Router) {

  }

  ngOnInit(): void {

  }

  showProfile() {
    this.isShowMessages = false;
    this.isShowProfile = !this.isShowProfile;
  }

  menuToggle() {
    this.homeComponent.changeMenuStyle();
  }

  showMessages() {
    this.isShowProfile = false;
    this.isShowMessages = !this.isShowMessages;
  }

  public logout() {
    this.loginService.logout();
    this.router.navigateByUrl("login");
  }

  public getUser() {
    this.http.get(GlobalVariable.BASE_API_URL + "sys/user/get/1")
      .map(response => response.json())
      .subscribe(
        result => {
          console.log(result);
        },
        error => {
          console.log(error);
        }
      )
  }

}
