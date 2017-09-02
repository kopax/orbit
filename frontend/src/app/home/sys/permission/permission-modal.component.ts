import {Component, Injectable, Input, OnInit} from "@angular/core";
import {Permission} from "../../../models/permission-model";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {PermissionService} from "./permission.service";
import * as GlobalVariable from "../../../globals";
import {Commons} from "../../../commons";
import {Router} from "@angular/router";

@Component({
  selector: 'permission-modal',
  templateUrl: './permission-modal.component.html',
  styleUrls: ['./permission.modal.css']
})

export class PermissionModalComponent implements OnInit {

  @Input()
  public parent: Permission;
  public permission: Permission = new Permission();
  public data: Permission[];
  public categories = [];
  public icons = ['home', 'cog', 'ravelry'];

  constructor(public activeModal: NgbActiveModal,
              public service: PermissionService,
              public router: Router) {
  }

  ngOnInit(): void {
    let _categories = this.service.category;
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
    if (!form.valid) {
      return;
    }

    this.service.add(this.permission).then(permission => {
      permission.children = [];
      this.data.push(permission);
      this.activeModal.close();
    }).catch(reason => {
      this.activeModal.close();
      Commons.errorHandler(reason, this.router);
    })
  }

}
