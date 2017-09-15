import {Component, OnInit} from "@angular/core";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {RoleService} from "./role.service";
import {Role} from "../../../models/role-model";

@Component({
  selector: "role-modal",
  templateUrl: "./role-modal.component.html",
  styleUrls: ['./role-modal.css']
})
export class RoleModalComponent implements OnInit {

  public role: Role = new Role();
  public state = 'add';

  constructor(private activeModal: NgbActiveModal,
              private service: RoleService) {

  }

  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }

  public save(form) {

  }

  public close() {
    this.activeModal.dismiss();
  }

}
