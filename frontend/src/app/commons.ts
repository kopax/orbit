import {Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {LayerAlert} from "./layers/layer.alert";
import {LayerConfirm} from "./layers/layer.confirm";

export class Commons {
  static errorHandler(error: any, router: Router, modalService: NgbModal) {
    let response = error._body ? JSON.parse(error._body) : {code: 100};
    switch (response.code) {
      case "100":
        console.log(error);
        break;
      case "403":
        router.navigateByUrl("error_403")
        break;
      case "401":
        router.navigateByUrl("login");
        break;
      case "1001":
        error(modalService, response.message);
        break;
      case "1002":
        error(modalService, response.message);
        break;
    }
  }

  static alert(modalService: NgbModal, options: { message: string, icon: string, iconCss: string }) {
    const modalRef = modalService.open(LayerAlert, {size: 'sm'});
    Object.assign(modalRef.componentInstance, options);
  }

  static error(modalService: NgbModal, message: string) {
    Commons.alert(modalService, {
      message: message,
      icon: 'frown-o',
      iconCss: 'danger'
    });
  }

  static confirm(modalService: NgbModal, message: string): Promise<any> {
    const modalRef = modalService.open(LayerConfirm, {size: 'sm', backdrop: 'static'});
    Object.assign(modalRef.componentInstance, {
      message: message,
      icon: 'question-circle-o',
      iconCss: 'warning'
    });
    return modalRef.result;
  }
}
