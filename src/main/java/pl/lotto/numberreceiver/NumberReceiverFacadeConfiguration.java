package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;

public class NumberReceiverFacadeConfiguration {
    NumberReceiverFacade createFacadeForTest(TicketRepository ticketRepository, LocalDateTime date) {
        DTOMapper dtoMapper = new DTOMapper();
        NumberValidator numberValidator = new NumberValidator();
        TicketGenerator ticketGenerator = new TicketGenerator(new IdGenerator(), new DrawDateGenerator(date));
        return new NumberReceiverFacade(numberValidator, ticketGenerator, ticketRepository, dtoMapper);
    }
}
