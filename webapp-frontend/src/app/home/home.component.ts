import {Component, Injectable, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.css']
})

@Injectable()
export class HomeComponent implements OnInit {

  constructor(public router: Router) {

  }

  ngOnInit(): void {
    //this.router.navigateByUrl("content");
  }


}
