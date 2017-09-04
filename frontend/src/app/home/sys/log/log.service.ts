import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import * as GlobalVariable from "../../../globals";

@Injectable()
export class LogService {

  private url_list = GlobalVariable.BASE_API_URL + "sys/log/list";

  constructor(public http: Http) {

  }

  public list(conditions: any, pageNo: number, pageSize: number): Promise<any> {
    let body = Object.assign(conditions, {pageNo: pageNo, pageSize: pageSize});
    return this.http
      .get(this.url_list, {params: body})
      .toPromise()
      .then(response => response.json().data)
      .catch(reason => Promise.reject(reason));
  }

}
