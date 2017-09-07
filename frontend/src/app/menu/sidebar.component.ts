import {Component, OnInit} from "@angular/core";
import {trigger, state, style, animate, transition} from '@angular/animations';
import * as GlobalVariable from "../globals";
import {Router} from "@angular/router";
import {User} from "../models/user-model";
import {Commons} from "../commons";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {HttpClient} from "@angular/common/http";

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
  private URL_MENUS = GlobalVariable.BASE_API_URL + "sys/permission/someones/menus";
  public images: string = GlobalVariable.PATH_IMAGES;
  public menus = [];
  public user:User;
  public active;
  public p_active;

  public constructor(public http: HttpClient,
                     public router: Router,
                     public modalSerivce: NgbModal) {
    let objUser = localStorage.getItem(GlobalVariable.CURRENT_USER);
    this.user = JSON.parse(objUser);
    this.http.get(this.URL_MENUS)
      .subscribe(
        result => {
          this.menus = result['data'];
          if (this.menus.length > 0) {
            for (let i = 0; i < this.menus.length; i++) {
              this.menus[i].state = "inactive";
            }
            this.active = this.menus[0];
            this.active.state = 'active';
            this.p_active = this.active.id;
          }
        },
        error => {
          Commons.errorHandler(error, this.router, this.modalSerivce);
        }
      )
  }

  ngOnInit(): void {

  }

  changeActive(menu, $event) {
    if (menu.id == this.active.id) {
      this.active.state = this.active.state == 'inactive' ? 'active' : 'inactive';
    } else {
      this.p_active = menu.id;
      this.active.state = 'inactive';
      menu.state = "active";
      this.active = menu;
    }
  }

  changeContent(menu, $event) {
    $event.stopPropagation();
    this.router.navigateByUrl(menu.action);
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
