import {Component, Input, OnInit} from "@angular/core"
import {Permission} from "../../../models/permission-model";
import 'rxjs/add/operator/map'
import {Http} from "@angular/http";
import {PermissionService} from "./permission.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "permission",
  templateUrl: "./permission.component.html",
  styleUrls: ["./permission.css"]
})

export class PermissionComponent implements OnInit {

  public permissions: Permission[] = [];
  public category;
  @Input()
  public message;

  public constructor(public http: Http,
                     public service: PermissionService,
                     public modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.service.getData(true).subscribe(data => {
      this.permissions = data;
    });
    this.category = this.service.category;
  }

  remove(dialog) {
    this.message = this.service.remove(this.permissions);
    this.modalService.open(dialog);
  }

}
