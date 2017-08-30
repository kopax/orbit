import {Permission} from "../../../models/permission-model";
import {Http} from "@angular/http";
import * as GlobalVariable from "../../../globals";
import {Injectable, OnInit} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";

@Injectable()
export class PermissionService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/permission/list";
  private url_delete = GlobalVariable.BASE_API_URL + "sys/permission/delete";
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
      return "请选择要删除的数据";
    }

    for (let i = 0; i < actives.length; i++) {
      if (actives[i].children.length != 0) {
        return "请先删除子菜单"
      }
    }

    let ids = [];
    actives.forEach(inst => {
      ids.push(inst.id);
    });

    this.http
      .delete(this.url_delete, {body: ids})
      .map(response => response.json())
      .subscribe(result => {
          console.log(result);
        },
        error => {

        });

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
