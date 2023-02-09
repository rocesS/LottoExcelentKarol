package pl.lotto.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumberReceiverFacadeConfiguration {
    @Bean
    NumberReceiverFacade numberReceiverFacade(TicketRepository ticketRepository) {
        NumberValidator numberValidator = new NumberValidator();
        TicketGenerator ticketGenerator = new TicketGenerator(new IdGenerator(), new DrawDateGenerator());
        return new NumberReceiverFacade(numberValidator, ticketGenerator, ticketRepository);
    }

    NumberReceiverFacade createFacadeForTest(TicketRepository ticketRepository) {
        return numberReceiverFacade(ticketRepository);
    }
}
