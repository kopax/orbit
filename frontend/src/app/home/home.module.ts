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
import {NgbAlertModule, NgbDropdownModule, NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import {AutoHeightDirective} from "../directive/auto-height.directive";
import {PermissionChildViewComponent} from "./sys/permission/permission-child-view.component";
import {PermissionService} from "./sys/permission/permission.service";


@NgModule({
  declarations: [
    HomeComponent,
    SidebarComponent,
    TopnavbarComponent,
    FooterComponent,
    ContentComponent,
    PermissionComponent,
    AutoHeightDirective,
    PermissionChildViewComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(appRoutes),
    NgbPaginationModule,
    NgbDropdownModule,
    NgbAlertModule
  ],
  providers: [
    PermissionService
  ]
})

export class HomeModule {

}
