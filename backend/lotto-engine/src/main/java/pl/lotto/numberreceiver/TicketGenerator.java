package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

class TicketGenerator {
    IdGenerator idGenerator;
    DrawDateGenerator drawDateGenerator;

    TicketGenerator(IdGenerator idGenerator, DrawDateGenerator drawDateGenerator) {
        this.idGenerator = idGenerator;
        this.drawDateGenerator = drawDateGenerator;
    }

    LotteryTicket generateTicket(Collection<Integer> numbers) {
        String id = idGenerator.generateId();
        LocalDateTime drawDate = drawDateGenerator.generateDrawDate(LocalDateTime.now());
        return new LotteryTicket(id, new ArrayList<>(numbers), drawDate.toString(), TicketMessage.VALID.message);
    }

    LotteryTicket generateInvalidTicket(Collection<Integer> numbers) {
        return new LotteryTicket(null, new ArrayList<>(numbers), null, TicketMessage.INVALID.message);
    }
}
