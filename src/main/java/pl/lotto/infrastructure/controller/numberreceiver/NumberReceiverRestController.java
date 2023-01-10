package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

@RestController
public class NumberReceiverRestController {

    NumberReceiverFacade numberReceiverFacade;

    NumberReceiverRestController(NumberReceiverFacade numberReceiverFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @PostMapping("/numbers")
    public ResponseEntity<LotteryTicketDto> inputNumbers(@RequestBody List<Integer> numbers) {
        LotteryTicketDto ticket = numberReceiverFacade.inputNumbers(numbers);
        if (ticket.message().equals("valid")) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(ticket);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ticket);
        }
    }
}



