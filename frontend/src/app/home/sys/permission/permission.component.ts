import {Component, Input, OnInit} from "@angular/core"
import {Permission} from "../../../models/permission-model";
import 'rxjs/add/operator/map'
import {Http} from "@angular/http";
import {PermissionService} from "./permission.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {LayerAlert} from "../../../layers/layer.alert";
import {PermissionModalComponent} from "./permission-modal.component";
import {Router} from "@angular/router";
import {Commons} from "../../../commons";

@Component({
  selector: "permission",
  templateUrl: "./permission.component.html",
  styleUrls: ["./permission.css"]
})

export class PermissionComponent implements OnInit {

  public permissions: Permission[] = [];
  public category;

  public constructor(public http: Http,
                     public service: PermissionService,
                     public modalService: NgbModal,
                     public router: Router) {
  }

  ngOnInit(): void {
    this.service.getData().subscribe(
      response => this.permissions = response.data,
      error => Commons.errorHandler(error, this.router)
    );
    this.category = this.service.category;
  }

  remove() {
    const modalRef = this.modalService.open(LayerAlert, {size: 'sm'});
    modalRef.componentInstance.message = this.service.remove(this.permissions);
    modalRef.componentInstance.icon = "frown-o";
  }

  add() {
    let actives = [];
    this.service.getActives(actives, this.permissions);

    const modalRef = this.modalService.open(PermissionModalComponent, {
      backdrop: 'static',
      size: 'lg'
    });
    modalRef.componentInstance.parent = actives[0] || {id: "-1", name: "根菜单"};
    modalRef.componentInstance.permission.parent = (actives[0] && actives[0].id) || "-1";
    modalRef.componentInstance.permission.category = 1;
    modalRef.componentInstance.data = this.permissions;
  }

}
