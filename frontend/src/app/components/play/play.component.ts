import {Component} from '@angular/core';
import {ReceiverService} from "../../services/receiver/receiver.service";
import {HttpErrorResponse} from "@angular/common/http";
import {LotteryTicket} from "../../LotteryTicket";


@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})
export class PlayComponent {
  inputNumbers: string[] = [];
  numbers: number[] = [];
  size: number = 6;
  min: number = 1;
  max: number = 99;
  id: string = '';
  drawDate: string = '';
  ticketAvailable: boolean = false;
  errorMessage = '';


  constructor(private receiverService: ReceiverService) {
  }

  onInput(value: string, index: number) {
    this.inputNumbers[index] = value;
  }

  onClickSubmit() {
    this.errorMessage = '';
    this.ticketAvailable = false;
    const isValid = this.inputNumbers.every(userNumber => {
      const number = Number(userNumber);
      return !isNaN(number);
    });

    if (!isValid) {
      this.errorMessage = 'Values must be numbers!';
      return;
    }

    if (this.inputNumbers.length < this.size) {
      this.errorMessage = 'Enter all numbers!';
      return;
    }

    const uniqueValues = [...new Set(this.inputNumbers)];
    if (uniqueValues.length !== this.size) {
      this.errorMessage = 'Numbers must be distinct!';
      return;
    }

    for (let value of uniqueValues) {
      // @ts-ignore
      if (value < this.min || value > this.max) {
        this.errorMessage = 'Numbers must be in range 1-99!';
        return;
      }
    }

      // @ts-ignore
      let lotteryTicketPromise = this.receiverService.inputNumbers(this.inputNumbers);
      lotteryTicketPromise.then(response => {
        this.id = response.id;
        this.numbers = response.numbers;
        this.drawDate = response.drawDate.replace("T", " ");
      });

      this.ticketAvailable = true;
  }

  onClickClear() {
    this.inputNumbers.splice(0, this.size);
  }
}
