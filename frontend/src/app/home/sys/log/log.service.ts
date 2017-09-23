import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import * as GlobalVariable from "../../../globals";
import {Log} from "../../../models/log-model";

@Injectable()
export class LogService {

  public url_list = GlobalVariable.BASE_API_URL + "sys/logs";
  public url_export = GlobalVariable.BASE_API_URL + "sys/logs/as-excel";

  constructor(public http: HttpClient) {

  }

  public list(conditions: any, pageNo: number, pageSize: number): Promise<any> {
    let body = Object.assign(conditions, {number: pageNo, size: pageSize});
    return this.http
      .get(this.url_list, {
        params: new HttpParams({fromObject: body})
      }).toPromise();
  }

}
