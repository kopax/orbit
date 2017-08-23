import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {trigger, state, style, animate, transition} from '@angular/animations';
import * as GlobalVariable from "../globals";

@Component({
  selector: 'side-bar',
  templateUrl: './sidebar.component.html',
  animations: [
    trigger("tiggerMenu", [
      state("inactive", style({display: 'none'})),
      state("active", style({display: 'block'})),
      transition('inactive => active', [
        animate(200, style({transform: 'translateX(0)'}))
      ]),
      transition('active => inactive', [
        animate(200, style({transform: 'translateX(-100%)'}))
      ])
    ])
  ]
})

export class SidebarComponent implements OnInit {
  public images: string = GlobalVariable.PATH_IMAGES;
  public menus = [];
  public active;

  public constructor(public http: Http) {
    this.http.get(GlobalVariable.BASE_API_URL + "sys/permission/get/someones/menus")
      .map(response => response.json())
      .subscribe(
        result => {
          if (result.status == GlobalVariable.RESULT_SUCCESS) {
            this.menus = result.data;
            if (this.menus.length > 0) {
              this.active = this.menus[0];
              this.active.state = 'active';
            }
          }
        },
        error => {

        }
      )
  }

  ngOnInit(): void {

  }

  changeActive(menu) {
    this.active.state = 'inactive';
    menu.state = "active";
    this.active = menu;
  }

}
