import {ContentComponent} from "./content.component";
import {HomeComponent} from "./home.component";
import {PermissionComponent} from "./sys/permission/permission.component";
import {LogComponent} from "./sys/log/log.component";

export const appRoutes = [{
  path: 'home',
  component: HomeComponent,
  children: [
    {path: '', component: ContentComponent},
    {path: 'content', component: ContentComponent},
    {path: 'permission', component: PermissionComponent},
    {path: 'log', component: LogComponent}
  ]
}];
