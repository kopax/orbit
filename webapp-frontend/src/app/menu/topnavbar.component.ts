import {Component, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";

@Component({
  selector: "top-navbar",
  templateUrl: "./topnavbar.component.html"
})

export class TopnavbarComponent implements OnInit {

  public images = GlobalVariable.images;

  constructor() {

  }

  ngOnInit(): void {

  }

  showMessages() {
    console.log("showMessages");
  }

}
