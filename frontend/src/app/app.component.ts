import {Component} from '@angular/core';
import 'rxjs/add/operator/merge';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  private globalClickCallbackFn: Function;
  private loginSuccessCallbackFn: Function;

  constructor() {

  }

  ngOnInit() {

  }

  ngOnDestroy() {
    if (this.globalClickCallbackFn) {
      this.globalClickCallbackFn();
    }
  }
}
