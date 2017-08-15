import {ContentComponent} from "./content.component";
import {HomeComponent} from "./home.component";

export const appRoutes = [{
  path: 'home',
  component: HomeComponent,
  children: [
    {path: '', component: ContentComponent},
    {path: 'content', component: ContentComponent}
  ]
}];
