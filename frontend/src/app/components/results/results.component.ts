import { Component } from '@angular/core';

import {AnnouncerService} from "../../services/announcer/announcer.service";

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css']
})
export class ResultsComponent {
  ticketId: string = '';
  yourNumbers: number[] = [];
  winningNumbers: number[] = [];
  hitNumbers: number = 0;
  message = ';'
  resultsAvailable: boolean = false;
  errorMessage = '';

  constructor(private announcerService: AnnouncerService){}

  onInput(value: string) {
    this.ticketId = value;
  }

  onClickCheck() {
    let lotteryResultPromise = this.announcerService.getLotteryResults(this.ticketId);

    const regexExp = /^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/gi;
    if (!regexExp.test(this.ticketId)) {
      this.errorMessage = 'Invalid ID'
      return;
    }

    lotteryResultPromise.then(response => {
      this.yourNumbers = response.yourNumbers;
      this.winningNumbers = response.winningNumbers;
      this.hitNumbers = response.hitNumbers;
      this.message = response.message;
    });


    if (this.message === 'invalid id') {
      this.errorMessage = 'Invalid ID';
      return;
    }

    if (this.message === 'the draw has not yet taken place') {
      this.errorMessage = 'The draw has not yet taken place'
      return;
    }

    this.resultsAvailable = true;
  }
}
