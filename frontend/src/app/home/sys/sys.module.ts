import {PermissionComponent} from "./permission/permission.component";
import {PermissionChildViewComponent} from "./permission/permission-child-view.component";
import {PermissionModalComponent} from "./permission/permission-modal.component";
import {LogComponent} from "./log/log.component";
import {RoleComponent} from "./role/role.component";
import {NgModule} from "@angular/core";
import {PermissionService} from "./permission/permission.service";
import {LogService} from "./log/log.service";
import {RoleService} from "./role/role.service";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {NgbDropdownModule, NgbPaginationModule, NgbAlertModule, NgbDatepickerModule} from "@ng-bootstrap/ng-bootstrap";
import {AutoHeightDirective} from "../../directive/auto-height.directive";
import {NotRepeatValidator} from "../../directive/validators/not-repeat.validator";
import {LocalDateTimePipe} from "../../pipe/LocalDateTimePipe";
import {TranslateModule} from "@ngx-translate/core";
import {RoleModalComponent} from "./role/role-modal.component";

@NgModule({
  declarations: [
    LocalDateTimePipe,
    AutoHeightDirective,
    NotRepeatValidator,
    PermissionComponent,
    PermissionChildViewComponent,
    PermissionModalComponent,
    LogComponent,
    RoleComponent,
    RoleModalComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    NgbPaginationModule,
    NgbDropdownModule,
    NgbAlertModule,
    NgbDatepickerModule,
    TranslateModule
  ],
  entryComponents: [
    PermissionModalComponent,
    RoleModalComponent
  ],
  providers: [
    PermissionService,
    LogService,
    RoleService
  ]
})
export class SysModule {

}
