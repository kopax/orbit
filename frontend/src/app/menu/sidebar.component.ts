import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {trigger, state, style, animate, transition} from '@angular/animations';
import * as GlobalVariable from "../globals";

@Component({
  selector: 'side-bar',
  templateUrl: './sidebar.component.html',
  animations: [
    trigger("tiggerMenu", [
      state("inactive", style({transform: 'scaleY(0)'})),
      state("active", style({transform: 'scaleY(1)'})),
      transition('inactive => active', animate('200ms ease-in')),
      transition('active => inactive', animate('200ms ease-out'))
    ])
  ]
})

export class SidebarComponent implements OnInit {
  public images: string = GlobalVariable.PATH_IMAGES;
  public menus = [];
  public active;
  public p_active;

  public constructor(public http: Http) {
    this.http.get(GlobalVariable.BASE_API_URL + "sys/permission/get/someones/menus")
      .map(response => response.json())
      .subscribe(
        result => {
          if (result.status == GlobalVariable.RESULT_SUCCESS) {
            this.menus = result.data;
            if (this.menus.length > 0) {
              for (let i = 0; i < this.menus.length; i++) {
                this.menus[i].state = "inactive";
              }
              this.active = this.menus[0];
              this.active.state = 'active';
              this.p_active = this.active.id;
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
    if (menu.id == this.active.id) {
      this.active.state = this.active.state == 'inactive' ? 'active' : 'inactive';
    } else {
      this.p_active = menu.id;
      this.active.state = 'inactive';
      menu.state = "active";
      this.active = menu;
    }
  }

  triggerMenuDone($event, menu) {
    if (menu.state == "inactive") {
      $event.element.style.display='none';
    }
  }

  triggerMenuStart($event, menu) {
    if (menu.state == "active") {
      $event.element.style.display='block';
    }
  }

}
