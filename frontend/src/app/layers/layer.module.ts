import {NgModule} from "@angular/core";
import {LayerAlert} from "./layer.alert";
import {LayerConfirm} from "./layer.confirm";

@NgModule({
  declarations: [
    LayerAlert,
    LayerConfirm
  ],
  entryComponents: [
    LayerAlert,
    LayerConfirm
  ]
})

export class LayerModule {

}
