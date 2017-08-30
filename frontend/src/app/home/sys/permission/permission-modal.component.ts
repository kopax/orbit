import {Component, Input, OnInit} from "@angular/core";
import {Permission} from "../../../models/permission-model";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'permission-modal',
  templateUrl: './permission-modal.component.html',
  styleUrls: ['./permission.modal.css']
})

export class PermissionModalComponent implements OnInit {

  public permission: Permission;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
  }

  close() {
    this.activeModal.dismiss();
  }

}
