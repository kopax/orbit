import {Injectable} from "@angular/core";
import * as GlobalVariable from "../../../globals";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Role} from "../../../models/role-model";

@Injectable()
export class RoleService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/roles";
  private url_check_code = GlobalVariable.BASE_API_URL + "/sys/role/checkCode/";
  private url_crud = GlobalVariable.BASE_API_URL + "sys/role";

  constructor(public http: HttpClient) {

  }

  public list(conditions: { keywords, number, size }): Observable<any> {
    return this.http.get(this.url_list, {params: new HttpParams({fromObject: conditions})});
  }

  public add(role: Role): Promise<Role> {
    return this.http.post(this.url_crud, role)
      .toPromise()
      .then(response => response['data'] as Role)
      .catch(reason => Promise.reject(reason));
  }

  public checkCode(id: number, code: string): boolean {
    let remote = this.url_check_code + code + "/" + id;
    let xhr = new XMLHttpRequest();
    let bit = true;
    xhr.withCredentials = true;
    xhr.open("get", remote, false);
    xhr.onreadystatechange = () => {
      if (xhr.readyState == 4 && xhr.status == 200) {
        if (xhr.responseText) {
          bit = JSON.parse(xhr.responseText).data as boolean;
        }
      }
    };
    xhr.send();
    return bit;
  }

}
