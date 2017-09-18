import {Component, OnInit} from "@angular/core";
import {Page} from "../../../models/page-model";
import {Role} from "../../../models/role-model";
import {RoleService} from "./role.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Commons} from "../../../commons";
import {RoleModalComponent} from "./role-modal.component";

@Component({
  selector: "role",
  templateUrl: "./role.component.html",
  styleUrls: ["./role.css"]
})
export class RoleComponent implements OnInit {

  public keywords: string = "";

  public page: Page<Role> = new Page<Role>();

  public allChekced = "";

  constructor(private service: RoleService,
              private router: Router,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.refreshList();
  }

  private refreshList() {
    this.service.list({keywords: this.keywords, number: 1, size: 10})
      .subscribe(response => {
        this.page = response['data'];
      }, error => {
        Commons.errorHandler(error, this.router, this.modalService);
      })
  }

  public search(event) {
    if (event.key && event.key != 'Enter') {
      return;
    }
    this.refreshList();
  }

  public add() {
    let modalRef = this.modalService.open(RoleModalComponent, {size: 'lg', backdrop: 'static'});
  }

  public remove() {
    let ids = [];
    this.page.content.forEach(role => {
      if (role['state'] == 'selected') {
        ids.push(role.id)
      }
    });
    this.service.remove(ids);
  }

  public selectRole(role) {
    if (role.id === 9999) {
      return;
    }
    role.state = role.state == 'selected' ? 'none' : 'selected';
    if (role.state === 'none') {
      this.allChekced = "";
    }
  }

  public selectAll() {
    this.page.content.forEach(role => {
      if (role.id !== 9999) {
        role['state'] = this.allChekced == 'checked' ? 'none' : 'selected';
      }
    });
    this.allChekced = this.allChekced == 'checked' ? '' : 'checked';
  }

}
