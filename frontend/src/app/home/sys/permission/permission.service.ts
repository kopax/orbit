import {Permission} from "../../../models/permission-model";
import {Http} from "@angular/http";
import * as GlobalVariable from "../../../globals";
import {Injectable, OnInit} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {Commons} from "../../../commons";

@Injectable()
export class PermissionService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/permission/list";
  private url_delete = GlobalVariable.BASE_API_URL + "sys/permission/delete";
  private url_add = GlobalVariable.BASE_API_URL + "sys/permission/add";
  public category = {"1": "菜单", "2": "按钮"};
  public subject = new Subject<Permission[]>();

  public constructor(public http: Http,
                     public router: Router) {
  }

  public getData(): Observable<any> {
    return this.http.get(this.url_list).map(response => response.json());
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
          console.log(error);
        });

  }

  public getActives(actives: Permission[], data: Permission[]) {
    data.forEach(t => {
      if (t['state'] && t['state'] === "active") {
        actives.push(t);
      }
      this.getActives(actives, t.children);
    });
  }

  add(permission: Permission): Promise<Permission> {
    return this.http.put(this.url_add, permission)
      .toPromise()
      .then(response => response.json().data as Permission)
      .catch(reason => Promise.reject(reason));
  }
}
