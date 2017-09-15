import {Injectable} from "@angular/core";
import * as GlobalVariable from "../../../globals";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class RoleService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/roles";

  constructor(public http: HttpClient) {

  }

  public list(conditions: { keywords, number, size }): Observable<any> {
    return this.http.get(this.url_list, {params: new HttpParams({fromObject: conditions})});
  }

}
