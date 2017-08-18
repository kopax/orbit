import {Http, RequestOptions, XHRBackend} from "@angular/http";
import * as GlobalVariable from "./globals";
import {Injectable} from "@angular/core";

@Injectable()
export class BaseHttp extends Http {
  constructor(backend: XHRBackend, options: RequestOptions) {
    super(backend, options);

    const user = localStorage.getItem(GlobalVariable.CURRENT_USER);
    const token = user ? JSON.parse(user).token : "-1";
    options.headers.set("Authorization", token);
  }
}
