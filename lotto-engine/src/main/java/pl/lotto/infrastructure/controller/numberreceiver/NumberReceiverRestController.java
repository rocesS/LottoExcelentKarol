package pl.lotto.infrastructure.controller.numberreceiver;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.ReceiverRequestDto;

import java.util.List;

@RestController
public class NumberReceiverRestController {

    private final NumberReceiverFacade numberReceiverFacade;

    NumberReceiverRestController(NumberReceiverFacade numberReceiverFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
    }

    @PostMapping("/numbers")
    public ResponseEntity<LotteryTicketDto> inputNumbers(@Valid @RequestBody ReceiverRequestDto request) {
        List<Integer> numbers = request.numbers();
        LotteryTicketDto ticket = numberReceiverFacade.inputNumbers(numbers);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ticket);
    }
}



