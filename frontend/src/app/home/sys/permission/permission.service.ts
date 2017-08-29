import {Permission} from "../../../models/permission-model";
import {Http} from "@angular/http";
import * as GlobalVariable from "../../../globals";
import {Injectable, OnInit} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

@Injectable()
export class PermissionService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/permission/list";
  public category = {"1": "菜单", "2": "按钮"};
  public data: Subject<Permission[]> = new Subject<Permission[]>();

  public constructor(public http: Http) {
  }

  public getData(isRefresh: boolean): Observable<Permission[]> {
    if (isRefresh) {
      this.loadData();
    }
    return this.data.asObservable();
  }

  public loadData() {
    this.http.get(this.url_list)
      .map(response => response.json())
      .subscribe(
        result => {
          this.data.next(result.data);
        },
        error => {
          console.log(error);
        }
      )
  }

  remove(data: Permission[]): string {
    let actives = [];
    this.getActives(actives, data);
    if (actives.length == 0) {
      return "Please select the data you want to remove!";
    }
  }

  getActives(actives: Permission[], data: Permission[]) {
    data.forEach(t => {
      if (t['state'] && t['state'] === "active") {
        actives.push(t);
      }
      this.getActives(actives, t.children);
    });
  }

}
