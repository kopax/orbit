import {Component, ElementRef, OnInit, ViewChild} from "@angular/core"
import {Permission} from "../../models/permission-model";
import 'rxjs/add/operator/map'
import {Http} from "@angular/http";
import * as GlobalVariable from "../../globals";

@Component({
  selector: "permission",
  templateUrl: "./permission.component.html",
  styleUrls: ["./permission.css"]
})

export class PermissionComponent implements OnInit {
  private url_list = GlobalVariable.BASE_API_URL + "sys/permission/list";
  category = {
    "1" : "菜单",
    "2" : "按钮"
  }
  permissions: Permission[] = [];
  level = 0;

  public constructor(public http: Http) {

  }

  ngOnInit(): void {
    this.http.get(this.url_list)
      .map(response => response.json())
      .subscribe(
        result => {
          this.permissions = result.data;
        },
        error => {
          console.log(error);
        }
      )
  }

}
