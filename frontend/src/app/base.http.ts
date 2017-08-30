import {Http, RequestOptions, RequestOptionsArgs, XHRBackend, Response, Request} from "@angular/http";
import * as GlobalVariable from "./globals";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BaseHttp extends Http {

  constructor(backend: XHRBackend, options: RequestOptions) {
    super(backend, options);
  }

  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
    const user = localStorage.getItem(GlobalVariable.CURRENT_USER);
    const token = user ? JSON.parse(user).token : "unknown";


    if (typeof url == "string") {
      if (!options) {
        options = new RequestOptions({});
      }
      options.headers.set("Authorization", token);
      options.withCredentials = true;
    } else {
      url.headers.set("Authorization", token);
      url.withCredentials = true;
    }
    return super.request(url, options);
  }
}
