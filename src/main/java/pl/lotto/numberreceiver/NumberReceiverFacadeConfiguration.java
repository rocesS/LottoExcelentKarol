package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class NumberReceiverFacadeConfiguration {

    NumberReceiverFacade createFacadeForTest(TicketRepository ticketRepository, LocalDateTime date) {
        NumberValidator numberValidator = new NumberValidator();
        TicketGenerator ticketGenerator = new TicketGenerator(new IdGenerator(), new DrawDateGenerator(date));
        return new NumberReceiverFacade(numberValidator, ticketGenerator, ticketRepository);
    }

    @Bean
    LocalDateTime date() {
        LocalDateTime randomDate = LocalDateTime.of(2017, 2, 13, 15, 56);
        return randomDate;
    }
}
