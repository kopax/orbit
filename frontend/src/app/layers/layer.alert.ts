import {Component, Input} from "@angular/core";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'layer-alert',
  template: `
    <div class="modal-body">
        <i class="fa fa-{{icon}}" style="color: red; font-size: 30px;"></i>
        <div style="position: absolute; top: 22px; left: 50px;">{{message}}</div>
    </div>
  `
})

export class LayerAlert {
  @Input() message;

  @Input() icon;

  constructor(public activeModal: NgbActiveModal) {}
}
