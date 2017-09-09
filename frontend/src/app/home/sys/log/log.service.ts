import {Injectable} from "@angular/core";
import * as GlobalVariable from "../../../globals";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable()
export class LogService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/log/list";
  public url_export = GlobalVariable.BASE_API_URL + "sys/log/export";

  constructor(public http: HttpClient) {

  }

  public list(conditions: any, pageNo: number, pageSize: number): Promise<any> {
    let body = Object.assign(conditions, {pageNo: pageNo, pageSize: pageSize});
    return this.http
      .get(this.url_list, {
        headers: {"content-type": "application/json"},
        params: new HttpParams({fromObject: body})
      })
      .toPromise()
      .then(response => response['data'])
      .catch(reason => Promise.reject(reason));
  }

}
