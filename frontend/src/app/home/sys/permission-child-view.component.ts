import {Component, Input, OnInit} from "@angular/core";
import {Permission} from "../../models/permission-model";

@Component({
  selector: 'permission-child-view',
  templateUrl: './permission-child-view.component.html',
  styleUrls: ['./permission-child-view.css']
})

export class PermissionChildViewComponent implements OnInit {

  @Input()
  public permissions: Permission[];

  @Input()
  public level;

  public category = {
    "1" : "菜单",
    "2" : "按钮"
  };

  ngOnInit(): void {

  }

  change($event, level) {
    console.log($event);
  }
}
