import {Component} from '@angular/core';

import {AnnouncerService} from "../../services/announcer/announcer.service";

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent {
  ticketId: string = '';
  yourNumbers: string | null | undefined;
  winningNumbers: string | null | undefined;
  hitNumbers: number = 0;
  message: string = ''
  resultsAvailable: boolean = false;
  errorMessage = '';

  constructor(private announcerService: AnnouncerService) {

  }

  onInput(value: string) {
    this.ticketId = value;
  }

  onClickCheck() {
    this.errorMessage = '';
    this.resultsAvailable = false;
    const regexExp = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/gi;
    if (!regexExp.test(this.ticketId)) {
      this.errorMessage = 'Invalid ID'
      return;
    }

    let lotteryResultPromise = this.announcerService.getLotteryResults(this.ticketId);

    lotteryResultPromise.then(response => {
      this.yourNumbers = response.yourNumbers;
      this.winningNumbers = response.winningNumbers;
      this.hitNumbers = response.hitNumbers;
      this.message = response.message;

      if (this.message == 'Invalid ID') {
        this.errorMessage = this.message;
        return;
      }

      if (this.message == 'The draw has not yet taken place') {
        this.errorMessage = this.message;
        return;
      }

      this.resultsAvailable = true;

    });


  }
}
