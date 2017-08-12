import {Component, HostListener, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router, ActivatedRouteSnapshot, RouterState, RouterStateSnapshot} from '@angular/router';
import 'rxjs/add/operator/merge';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private globalClickCallbackFn: Function;
  private loginSuccessCallbackFn: Function;
  public home: boolean = false;

  constructor(public router: Router,
              public activatedRoute: ActivatedRoute) {

  }

  public homeView() {
    this.home = true;
  }

  public loginView() {
    this.home = false;
  }


  ngOnInit() {

  }

  ngOnDestroy() {
    if (this.globalClickCallbackFn) {
      this.globalClickCallbackFn();
    }
  }
}
