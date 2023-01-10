package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumberReceiverFacadeConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    IdGenerator idGenerator() {
        return new IdGenerator();
    }

    @Bean
    NumberReceiverFacade numberReceiverFacade(TicketRepository ticketRepository, Clock clock) {
        NumberValidator numberValidator = new NumberValidator();
        TicketGenerator ticketGenerator = new TicketGenerator(idGenerator(), new DrawDateGenerator(LocalDateTime.now(clock)));
        return new NumberReceiverFacade(numberValidator, ticketGenerator, ticketRepository);
    }

    NumberReceiverFacade createFacadeForTest(TicketRepository ticketRepository, Clock clock) {
        return numberReceiverFacade(ticketRepository, clock);
    }
}
