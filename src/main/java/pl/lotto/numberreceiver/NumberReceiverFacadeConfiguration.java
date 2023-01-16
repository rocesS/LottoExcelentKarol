package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
class NumberReceiverFacadeConfiguration {

    @Bean
    Clock clock() {
        return Clock.system(ZoneId.systemDefault());
    }

    @Bean
    NumberReceiverFacade numberReceiverFacade(TicketRepository ticketRepository, Clock clock) {
        NumberValidator numberValidator = new NumberValidator();
        TicketGenerator ticketGenerator = new TicketGenerator(new IdGenerator(), new DrawDateGenerator(), clock);
        return new NumberReceiverFacade(numberValidator, ticketGenerator, ticketRepository);
    }

    NumberReceiverFacade createFacadeForTest(TicketRepository ticketRepository, Clock clock) {
        return numberReceiverFacade(ticketRepository, clock);
    }
}
