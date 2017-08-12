import {ContentComponent} from "./content.component";
import {HomeComponent} from "./home.component";

export const appRoutes = [{
  path: '',
  component: HomeComponent,
  children: [
    {path: '', component: ContentComponent},
    {path: 'content', component: ContentComponent}
  ]
}];
