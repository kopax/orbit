import {Directive, HostBinding} from "@angular/core"

@Directive ({
  selector: '[auto-height]'
})

export class AutoHeightDirective {
  @HostBinding("style.height.px") height = window.innerHeight - 49;
}
