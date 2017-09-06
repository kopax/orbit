import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {Routes, RouterModule} from "@angular/router"
import {appRoutes} from "./home.routes";
import {ContentComponent} from "./content.component";
import {HomeComponent} from "./home.component";
import {SidebarComponent} from "../menu/sidebar.component";
import {TopnavbarComponent} from "../menu/topnavbar.component";
import {FooterComponent} from "./footer.component";
import {PermissionComponent} from "./sys/permission/permission.component";
import {
  NgbAlertModule, NgbDatepicker, NgbDatepickerModule, NgbDropdownModule,
  NgbPaginationModule
} from "@ng-bootstrap/ng-bootstrap";
import {AutoHeightDirective} from "../directive/auto-height.directive";
import {PermissionChildViewComponent} from "./sys/permission/permission-child-view.component";
import {PermissionService} from "./sys/permission/permission.service";
import {PermissionModalComponent} from "./sys/permission/permission-modal.component";
import {FormsModule} from "@angular/forms";
import {NotRepeatValidator} from "../directive/validators/not-repeat.validator";
import {LogComponent} from "./sys/log/log.component";
import {LogService} from "./sys/log/log.service";
import {LocalDateTimePipe} from "../pipe/LocalDateTimePipe";


@NgModule({
  declarations: [
    AutoHeightDirective,
    NotRepeatValidator,
    HomeComponent,
    SidebarComponent,
    TopnavbarComponent,
    FooterComponent,
    ContentComponent,
    PermissionComponent,
    PermissionChildViewComponent,
    PermissionModalComponent,
    LogComponent,
    LocalDateTimePipe
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(appRoutes),
    NgbPaginationModule,
    NgbDropdownModule,
    NgbAlertModule,
    FormsModule,
    NgbDatepickerModule
  ],
  entryComponents: [
    PermissionModalComponent
  ],
  providers: [
    PermissionService,
    LogService
  ]
})

export class HomeModule {

}
