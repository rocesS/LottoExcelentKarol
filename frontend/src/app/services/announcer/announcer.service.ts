import { Injectable } from '@angular/core';
import { environment } from 'src/environment';
import { LotteryResult } from "../../LotteryResult";

@Injectable({
  providedIn: 'root'
})
export class AnnouncerService {
  private apiServerUrl = environment.apiBaseUrl;

  public getLotteryResults(ticketId: string): Promise<LotteryResult> {
    return fetch(`${this.apiServerUrl}/announcement/${ticketId}`, {
      method: 'GET',
      headers: {'Content-Type': 'application/json'},
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(response.statusText);
        }
        return response.json();
      })
      .then(data => data as LotteryResult)
      .catch(error => {
        console.error(error);
        throw error;
      });
  }
}

