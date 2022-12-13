package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.InputNumbersDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class NumberReceiverFacade {
    NumberValidator numberValidator;
    DrawDateGenerator drawDateGenerator;
    IdGenerator idGenerator;
    TicketGenerator ticketGenerator;
    TicketRepository ticketRepository;

    NumberReceiverFacade(NumberValidator numberValidator, DrawDateGenerator drawDateGenerator, IdGenerator idGenerator, TicketGenerator ticketGenerator, TicketRepository ticketRepository) {
        this.numberValidator = numberValidator;
        this.drawDateGenerator = drawDateGenerator;
        this.idGenerator = idGenerator;
        this.ticketGenerator = ticketGenerator;
        this.ticketRepository = ticketRepository;
    }
    public InputNumbersDto inputNumbers(List<Integer> numbersFromUser) {
        if (numberValidator.validate(numbersFromUser)) {
            LocalDateTime drawDate = drawDateGenerator.generateDrawDate();
            UUID id = idGenerator.generateId();
            String message = "valid";
            LotteryTicketDto lotteryTicket = ticketGenerator.generateTicket(id, numbersFromUser, drawDate);
            return new InputNumbersDto(lotteryTicket, message);
        }
        return new InputNumbersDto(null, "invalid");
    }

    public LotteryTicketDto retrieveUserNumbers(UUID id) {
        return ticketRepository.getTicketById(id);
    }
}
