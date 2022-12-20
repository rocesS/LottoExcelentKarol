package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

interface TicketRepository {

    void addUserNumbers(UUID id, LotteryTicket lotteryTicket);

    LotteryTicket getTicketById(UUID id);

    AllUserNumbersByDate retrieveAllUsersByDate(LocalDateTime date);
}
