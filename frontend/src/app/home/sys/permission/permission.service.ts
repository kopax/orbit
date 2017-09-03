import {Permission} from "../../../models/permission-model";
import {Headers, Http} from "@angular/http";
import * as GlobalVariable from "../../../globals";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {toPromise} from "rxjs/operator/toPromise";

@Injectable()
export class PermissionService {

  //remote urls
  private url_add = GlobalVariable.BASE_API_URL + "sys/permission/add";
  private url_list = GlobalVariable.BASE_API_URL + "sys/permission/list";
  private url_delete = GlobalVariable.BASE_API_URL + "sys/permission/delete";
  private url_update = GlobalVariable.BASE_API_URL + "sys/permission/update";
  private url_change_sort = GlobalVariable.BASE_API_URL + "sys/permission/exchangeSort";

  public constructor(public http: Http,
                     public router: Router) {
  }

  public getData(): Observable<any> {
    return this.http.get(this.url_list).map(response => response.json());
  }

  public remove(actives: Permission[]): Promise<any> {
    let ids = [];
    actives.forEach(inst => {
      ids.push(inst.id);
    });
    return this.http
      .delete(this.url_delete, {body: ids})
      .toPromise()
      .then(response => response.json().data)
      .catch(error => Promise.reject(error));
  }

  public add(permission: Permission): Promise<Permission> {
    return this.http.put(this.url_add, permission)
      .toPromise()
      .then(response => response.json().data as Permission)
      .catch(reason => Promise.reject(reason));
  }

  public update(permission: Permission): Promise<Permission> {
    return this.http.put(this.url_update, permission)
      .toPromise()
      .then(response => response.json().data as Permission)
      .catch(reason => Promise.reject(reason));
  }

  public getActives(actives: Permission[], data: Permission[]) {
    data.forEach(t => {
      if (t['state'] && t['state'] === "active") {
        actives.push(t);
      }
      this.getActives(actives, t.children);
    });
  }

  public changeSort(data: Permission[]): Promise<any> {
    let body = [];
    data.forEach(inst => body.push({id: inst.id, sort: inst.sort}));
    let headers = new Headers();
    headers.set("content-type", "application/json");
    return this.http.patch(this.url_change_sort, JSON.stringify(body), {headers: headers})
      .toPromise()
      .then(response => response.json())
      .catch(reason => Promise.reject(reason));
  }
}
