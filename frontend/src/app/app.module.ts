import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './auth/login/login.component';
import {Http, HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {appRoutes} from './app.routes';
import {LoginService} from "./auth/login/login.service";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {HomeModule} from "./home/home.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BaseHttp} from "./base.http";
import {LayerModule} from "./layers/layer.module";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    FormsModule,
    HomeModule,
    RouterModule.forRoot(appRoutes),
    LayerModule
  ],
  providers: [LoginService, {provide: Http, useClass: BaseHttp}],
  bootstrap: [AppComponent]
})
export class AppModule {

}
