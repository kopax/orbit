import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HttpModule, Jsonp, JsonpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {appRoutes} from './app.routes';
import {LoginService} from "./login/login.service";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    JsonpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [LoginService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
