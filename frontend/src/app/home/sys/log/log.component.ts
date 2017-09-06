import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {LogService} from "./log.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Commons} from "../../../commons";
import {Log} from "../../../models/log-model";
import {Page} from "../../../models/page-model";

@Component({
  selector: 'log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.css']
})
export class LogComponent implements OnInit{

  public conditions = {
    keywords: '',
    begin: Date.now(),
    end: Date.now()
  }
  public page: Page<Log> = new Page<Log>();
  //@Output() pageChange: EventEmitter<any> = new EventEmitter();

  constructor(public logService:LogService,
              public router: Router,
              public modalService: NgbModal) {

  }

  public pageChange(event) {
    if (!isFinite(event) && event != 'size') {
      return;
    }
    this.query(event == 'size' ? 1 : event, this.page.size, {});
  }

  ngOnInit(): void {
    this.query(1, 10, {});
  }

  search(event) {
    console.log(this.conditions);
    console.log(event);
  }

  query(pageNumber, pageSize, conditions) {
    this.logService
      .list(conditions, pageNumber, pageSize)
      .then(data => this.page = data as Page<Log>)
      .catch(reason => Commons.errorHandler(reason, this.router, this.modalService));
  }

}
