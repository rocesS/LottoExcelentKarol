package pl.lotto.numberreceiver;

import org.springframework.stereotype.Repository;
import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;

import java.time.LocalDateTime;
import java.util.UUID;

interface TicketRepository {

    void addUserNumbers(UUID id, LotteryTicket lotteryTicket);

    LotteryTicket getTicketById(UUID id);

    AllUserNumbersByDate retrieveAllUsersByDate(LocalDateTime date);
}
