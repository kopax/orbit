import {Component, OnInit} from "@angular/core";
import {LogService} from "./log.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Commons} from "../../../commons";
import {Log} from "../../../models/log-model";

@Component({
  selector: 'log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.css']
})
export class LogComponent implements OnInit{

  public data: Log[] = [];

  constructor(public logService:LogService,
              public router: Router,
              public modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.logService
      .list({}, 1, 10)
      .then(data => this.data = data.content)
      .catch(reason => Commons.errorHandler(reason, this.router, this.modalService));
  }

}
