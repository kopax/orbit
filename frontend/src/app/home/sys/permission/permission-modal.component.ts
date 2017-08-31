import {Component, Input, OnInit} from "@angular/core";
import {Permission} from "../../../models/permission-model";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {PermissionService} from "./permission.service";
import * as GlobalVariable from "../../../globals";

@Component({
  selector: 'permission-modal',
  templateUrl: './permission-modal.component.html',
  styleUrls: ['./permission.modal.css']
})

export class PermissionModalComponent implements OnInit {

  @Input()
  public parent: Permission;
  public permission: Permission = new Permission();
  public categories = [];

  constructor(public activeModal: NgbActiveModal,
              public permissionSerivce: PermissionService) {
  }

  ngOnInit(): void {
    let _categories = this.permissionSerivce.category;
    for (let key in _categories) {
      if (_categories.hasOwnProperty(key)) {
        this.categories.push({value: key, text: _categories[key]});
      }
    }
  }

  close() {
    this.activeModal.dismiss();
  }

  save(form) {
    if (form.valid) {
      let result = this.permissionSerivce.add(this.permission);
      if (result == GlobalVariable.RESULT_SUCCESS) {
        this.activeModal.close();
      } else {
        this.activeModal.close("123213123123");
      }
    }
    console.log(form);
    console.log(this.permission);
  }

}
