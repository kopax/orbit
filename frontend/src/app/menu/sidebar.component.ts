import {Component, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";
import {Http} from "@angular/http";

@Component ({
  selector: 'side-bar',
  templateUrl: './sidebar.component.html'
})

export class SidebarComponent implements OnInit{
  public images: string = GlobalVariable.PATH_IMAGES;
  public menus;

  public constructor(public http: Http) {

  }

  ngOnInit(): void {
    this.http.get(GlobalVariable.BASE_API_URL + "sys/permission/getMenusOfSomeone")
      .map(response => response.json())
      .subscribe(
        result => {
          console.log(result.data);
        },
        error => {

        }
      )
  }

}
