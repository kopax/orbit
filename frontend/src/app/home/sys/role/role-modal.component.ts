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

  public treeData: any[] = [{
    name: "folder",
    iconClass:"ngtree-none-icon fa fa-file-text-o",
    nodes: [{
      name: 'file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    }]
  },{
    name: 'another folder',
    iconClass:"ngtree-none-icon fa fa-file-text-o",
    nodes:[{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    },{
      name: 'another file',
      iconClass:"ngtree-none-icon fa fa-file-text-o"
    }]
  }];

  public treeConfig : any = {
    dataMap:{
      children:"nodes"
    }
  }

  constructor(private activeModal: NgbActiveModal,
              private service: RoleService) {

  }

  ngOnInit(): void {

  }

  public save(form) {

  }

  public close() {
    this.activeModal.dismiss();
  }

}
