import {Component, OnInit} from "@angular/core";
import * as GlobalVariable from "../globals";

@Component ({
  selector: 'side-bar',
  templateUrl: './sidebar.component.html'
})

export class SidebarComponent implements OnInit{
  public images: string = GlobalVariable.images;
  ngOnInit(): void {

  }

}
