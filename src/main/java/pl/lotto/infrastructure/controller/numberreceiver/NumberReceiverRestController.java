package pl.lotto.infrastructure.controller.numberreceiver;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.RequestDto;

@RestController
public class NumberReceiverRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    NumberReceiverRestController(NumberReceiverFacade numberReceiverFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @PostMapping("/numbers")
    public ResponseEntity<LotteryTicketDto> inputNumbers(@Valid @RequestBody RequestDto request) {
        List<Integer> numbers = request.numbers();
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



