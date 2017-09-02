import {Router} from "@angular/router";

export class Commons {
  static errorHandler(error: any, router: Router) {
    var code = error._body ? JSON.parse(error._body).code : "100";
    switch(code) {
      case "100":
        console.log(error);
        break;
      case "403":
        router.navigateByUrl("403")
        break;
      case "401":
        router.navigateByUrl("login");
        break;
    }
  }
}
