package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TicketGenerator {
    LotteryTicketDto generateTicket(UUID id, List<Integer> numbers, LocalDateTime drawDate) {
        return new LotteryTicketDto(id, numbers, drawDate);
    }
}
