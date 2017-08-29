import {Component, Input, OnInit} from "@angular/core";
import {Permission} from "../../../models/permission-model";
import {PermissionService} from "./permission.service";

@Component({
  selector: 'permission-child-view',
  templateUrl: './permission-child-view.component.html',
  styleUrls: ['./permission-child-view.css']
})

export class PermissionChildViewComponent implements OnInit {

  @Input()
  public permissions: Permission[];

  @Input()
  public parent;

  @Input()
  public level: number;

  public levelClass;

  public category;

  public constructor(public service: PermissionService) {

  }

  ngOnInit(): void {
    this.category = this.service.category;
    this.levelClass = 'level-' + this.level;
  }

  change(inst, event) {
    event.stopPropagation();
    inst.spread = !inst.spread;
  }

  selectPermission(inst, event) {
    inst.state = inst.state === 'active' ? null : 'active';
  }
}
