package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumberReceiverFacadeConfiguration {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    NumberReceiverFacade numberReceiverFacade(TicketRepository ticketRepository, Clock clock) {
        NumberValidator numberValidator = new NumberValidator();
        TicketGenerator ticketGenerator = new TicketGenerator(new IdGenerator(), new DrawDateGenerator(LocalDateTime.now(clock)));
        return new NumberReceiverFacade(numberValidator, ticketGenerator, ticketRepository);
    }

    NumberReceiverFacade createFacadeForTest(TicketRepository ticketRepository, Clock clock) {
        return numberReceiverFacade(ticketRepository, clock);
    }

    @Bean
    TicketRepository ticketRepository(){
        return new TicketRepository() {
            @Override
            public void addUserNumbers(UUID id, LotteryTicket lotteryTicket) {

            }

            @Override
            public LotteryTicket getTicketById(UUID id) {
                return null;
            }

            @Override
            public AllUserNumbersByDate retrieveAllUsersByDate(LocalDateTime date) {
                return null;
            }
        };
    }

//    @Bean
//    LocalDateTime date() {
//        return LocalDateTime.of(2017, 2, 13, 15, 56);
//    }
}
