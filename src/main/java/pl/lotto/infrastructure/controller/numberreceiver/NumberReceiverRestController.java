package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.List;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

public class NumberReceiverRestController {

    NumberReceiverFacade numberReceiverFacade;

    public void f(){
        LotteryTicketDto lotteryTicketDto = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6));
    }
}
