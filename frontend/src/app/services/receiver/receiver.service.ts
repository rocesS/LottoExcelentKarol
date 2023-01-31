import { Injectable } from '@angular/core';
import { environment } from "../../../environment";
import { LotteryTicket } from "../../LotteryTicket";

@Injectable({
  providedIn: 'root'
})
export class ReceiverService {
  private apiServerUrl = environment.apiBaseUrl;

  public inputNumbers(numbers: number[]): Promise<LotteryTicket> {
    return fetch(`${this.apiServerUrl}/numbers`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({numbers: numbers})
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(response.statusText);
        }
        return response.json();
      })
      .then(data => data as LotteryTicket)
      .catch(error => {
        console.error(error);
        throw error;
      });
  }
}

