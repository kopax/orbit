import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import * as GlobalVariable from "../../../globals";

@Injectable()
export class LogService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/log/list";
  public url_export = GlobalVariable.BASE_API_URL + "sys/log/export";

  constructor(public http: HttpClient) {

  }

  public list(conditions: any, pageNo: number, pageSize: number): Promise<any> {
    let body = Object.assign(conditions, {number: pageNo, size: pageSize});
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
