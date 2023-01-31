import { Component } from '@angular/core';
import {ReceiverService} from "./services/receiver/receiver.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title: string = "LottoGameFrontend";
}
