package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class InputNumbersDto {
    LotteryTicketDto lotteryTicket;
    String validationMessage;

    public InputNumbersDto(LotteryTicketDto lotteryTicket, String validationMessage) {
        this.lotteryTicket = lotteryTicket;
        this.validationMessage = validationMessage;
    }

}
