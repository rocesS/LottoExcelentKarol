package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TicketRepository {
    Map<UUID, LotteryTicketDto> userNumbers = new HashMap<>();
    void addUserNumbers(UUID id, LotteryTicketDto lotteryTicket) {
        userNumbers.put(id, lotteryTicket);
    }

    LotteryTicketDto getTicketById(UUID id) {
        return userNumbers.get(id);
    }
}
