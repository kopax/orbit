import {Permission} from "../../../models/permission-model";
import * as GlobalVariable from "../../../globals";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {HttpParams} from "@angular/common/http";

@Injectable()
export class PermissionService {

  //remote urls
  private url_add = GlobalVariable.BASE_API_URL + "sys/permissions";
  private url_list = GlobalVariable.BASE_API_URL + "sys/permissions";
  private url_delete = GlobalVariable.BASE_API_URL + "sys/permissions";
  private url_update = GlobalVariable.BASE_API_URL + "sys/permissions";
  private url_change_sort = GlobalVariable.BASE_API_URL + "sys/permission/exchangeSort";
  private url_user_menus = GlobalVariable.BASE_API_URL + "/user/menus";

  public constructor(public http: HttpClient,
                     public router: Router) {
  }

  public getData(): Observable<any> {
    return this.http.get(this.url_list);
  }

  public findUserMenus(): Promise<Permission[]> {
    return this.http.get(this.url_user_menus)
      .toPromise()
      .then(body => body as Permission[])
      .catch(reason => Promise.reject(reason));
  }

  public remove(actives: Permission[]): Promise<any> {
    let ids = [];
    actives.forEach(inst => {
      ids.push(inst.id);
    });
    let params = new HttpParams({fromObject: {ids: ids.join(",")}});
    return this.http
      .delete(this.url_delete, {params: params})
      .toPromise()
      .then(response => response['data'])
      .catch(error => Promise.reject(error));
  }

  public add(permission: Permission): Promise<Permission> {
    return this.http.post(this.url_add, permission)
      .toPromise()
      .then(body => body as Permission)
      .catch(reason => Promise.reject(reason));
  }

  public update(permission: Permission): Promise<Permission> {
    return this.http.put(this.url_update, permission)
      .toPromise()
      .then(response => response['data'] as Permission)
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
    let headers = new HttpHeaders({"content-type": "application/json"});
    return this.http.put(this.url_change_sort, JSON.stringify(body), {headers: headers})
      .toPromise()
      .then(response => response)
      .catch(reason => Promise.reject(reason));
  }
}
