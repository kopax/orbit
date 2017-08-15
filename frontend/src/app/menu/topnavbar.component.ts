import {Component, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";
import {HomeComponent} from "../home/home.component";

@Component({
  selector: "top-navbar",
  templateUrl: "./topnavbar.component.html"
})

export class TopnavbarComponent implements OnInit {

  public images = GlobalVariable.images;
  public isShowMessages: boolean = false;
  public isShowProfile: boolean = false;

  constructor(public homeComponent: HomeComponent) {

  }

  ngOnInit(): void {

  }

  showProfile() {
    this.isShowMessages = false;
    this.isShowProfile = !this.isShowProfile;
  }

  menuToggle() {
    console.log("menuToggle");
    this.homeComponent.changeMenuStyle();
  }

  showMessages() {
    this.isShowProfile = false;
    this.isShowMessages = !this.isShowMessages;
  }

}
