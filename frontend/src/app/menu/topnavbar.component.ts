import {Component, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";
import {HomeComponent} from "../home/home.component";
import {LoginComponent} from "../auth/login/login.component";
import {LoginService} from "../auth/login/login.service";
import {Router} from "@angular/router";

@Component({
  selector: "top-navbar",
  templateUrl: "./topnavbar.component.html"
})

export class TopnavbarComponent implements OnInit {

  public images = GlobalVariable.PATH_IMAGES;
  public isShowMessages: boolean = false;
  public isShowProfile: boolean = false;

  constructor(public homeComponent: HomeComponent,
              public loginService: LoginService,
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

}
