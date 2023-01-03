package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.UUID;

interface TicketRepository {

    void addUserNumbers(UUID id, LotteryTicket lotteryTicket);

    LotteryTicket getTicketById(UUID id);

    AllUserNumbersByDate retrieveAllUsersByDate(LocalDateTime date);
}
