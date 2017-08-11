import {Component, OnInit} from "@angular/core";
import {AppComponent} from "../app.component";

@Component({
  selector: 'home',
  templateUrl: './home.component.html'
})

export class HomeComponent implements OnInit {

  constructor(public appComponent: AppComponent) {

  }

  ngOnInit(): void {
    this.appComponent.showMenu();
  }

}
