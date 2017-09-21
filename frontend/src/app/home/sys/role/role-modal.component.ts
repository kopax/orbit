import {Component, OnInit, ViewChild} from "@angular/core";
import {NgbActiveModal, NgbModal, NgbTooltip} from "@ng-bootstrap/ng-bootstrap";
import {RoleService} from "./role.service";
import {Role} from "../../../models/role-model";
import {isUndefined} from "util";
import {TranslateService} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {Commons} from "../../../commons";
import {PermissionService} from "../permission/permission.service";
import {Permission} from "../../../models/permission-model";
import {MTreeNode} from "../../../m-tree/m-tree-node";

@Component({
  selector: "role-modal",
  templateUrl: "./role-modal.component.html",
  styleUrls: ['./role-modal.css']
})
export class RoleModalComponent implements OnInit {

  public role: Role = new Role();
  public state = 'add';
  public nodes: MTreeNode[];

  @ViewChild("tipCode") public tipCode: NgbTooltip;
  @ViewChild("tipName") public tipName: NgbTooltip;

  constructor(private activeModal: NgbActiveModal,
              private service: RoleService,
              private translate: TranslateService,
              private router: Router,
              private modalService: NgbModal,
              private permissionSerivce: PermissionService) {
  }

  ngOnInit(): void {
    this.permissionSerivce.getData()
      .subscribe(
        response => this.nodes = this.generateTreeNodes(response.data as Permission[]),
        error => Commons.errorHandler(error, this.router, this.modalService)
      );
  }

  public generateTreeNodes(data: Permission[]) {
    const nodes = [];
    data.forEach(permission => {
      if (permission.category == 1) {
        nodes.push({
          id: permission.id,
          name: permission.name,
          spread: false,
          children: this.generateTreeNodes(permission.children)
        })
      }
    });
    return nodes;
  }

  public save() {
    if (!this.checkCode() || !this.checkName()) {
      return;
    }
    this.service.add(this.role)
      .then(role => {
        this.role = role;
        this.state = "lock";
      })
      .catch(reason => Commons.errorHandler(reason, this.router, this.modalService))
  }

  public close() {
    this.activeModal.dismiss();
  }

  public checkCode(): boolean {
    this.tipCode.close();
    if (isUndefined(this.role.code) || this.role.code.trim() === "") {
      this.tipShow(this.tipCode, this.translate.getParsedResult({}, "role.validator.code.not.empty"));
      return false;
    }
    if (!this.service.checkCode(isUndefined(this.role.id) || this.role.id == null || this.role.id == "" ? "-1" : this.role.id, this.role.code)) {
      this.tipShow(this.tipCode, this.translate.getParsedResult({}, "role.validator.code.not.repeat"));
      return false;
    }
    return true;
  }

  public checkName(): boolean {
    if (isUndefined(this.role.name) || this.role.name == null || this.role.name.trim() === "") {
      this.tipShow(this.tipName, this.translate.getParsedResult({}, "role.validator.name.not.empty"));
      return false;
    }
    return true;
  }

  private tipShow(tip, message) {
    tip.open({message: message});
    setTimeout(() => tip.close(), 2000);
  }

}
