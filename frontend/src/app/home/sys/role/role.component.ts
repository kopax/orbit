import {Component, OnInit} from "@angular/core";
import {Page} from "../../../models/page-model";
import {Role} from "../../../models/role-model";
import {RoleService} from "./role.service";

@Component({
  selector: "role",
  templateUrl: "./role.component.html"
})
export class RoleComponent implements OnInit {

  public keywords: string;

  public page: Page<Role> = new Page<Role>();

  constructor(private service: RoleService) {
  }

  ngOnInit(): void {
    this.service.list({keywords: null, number: 1, size: 10})
      .subscribe(response => {

      })

  }

}
