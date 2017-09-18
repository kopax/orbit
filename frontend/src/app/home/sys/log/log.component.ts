import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {LogService} from "./log.service";
import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Commons} from "../../../commons";
import {Log} from "../../../models/log-model";
import {Page} from "../../../models/page-model";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.css']
})
export class LogComponent implements OnInit {

  public conditions = {
    keywords: '',
    begin: undefined,
    end: undefined
  };
  public page: Page<Log> = new Page<Log>();

  constructor(public logService: LogService,
              public router: Router,
              public modalService: NgbModal) {

  }

  private getConditions() {
    let _conditions = {};
    if (this.conditions.keywords != '') {
      _conditions['keywords'] = this.conditions.keywords;
    }
    if (this.conditions.begin != undefined) {
      let begin = this.conditions.begin;
      _conditions['begin'] = [begin.year, begin.month, begin.day].join("-");
    }
    if (this.conditions.end != undefined) {
      let end = this.conditions.end;
      _conditions['end'] = [end.year, end.month, end.day].join("-");
    }
    return _conditions;
  }

  public pageChange(event) {
    if (!isFinite(event) && event != 'size') {
      return;
    }
    this.query(event == 'size' ? 1 : event, this.page.size, this.getConditions());
  }

  ngOnInit(): void {
    this.query(1, 10, {});
  }

  search(event) {
    if (event.key && event.key != 'Enter') {
      return;
    }
    this.query(1, this.page.size, this.getConditions());
  }

  query(pageNumber, pageSize, conditions) {
    this.logService
      .list(conditions, pageNumber, pageSize)
      .then(data => this.page = data as Page<Log>)
      .catch(reason => Commons.errorHandler(reason, this.router, this.modalService));
  }

  export() {
    let _conditions = this.getConditions();
    location.href = this.logService.url_export +
                        "?keywords=" + (_conditions['keywords'] || '') +
                        "&begin=" + (_conditions['begin'] || '') +
                        "&end=" + (_conditions['end'] || '');
  }

  resetConditions() {
    this.conditions = {
      keywords: '',
      begin: undefined,
      end: undefined
    }
  }

}
