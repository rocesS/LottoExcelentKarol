package pl.lotto.numberreceiver;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

class TicketGenerator {

    Clock clock;

    IdGenerator idGenerator;
    DrawDateGenerator drawDateGenerator;

    TicketGenerator(IdGenerator idGenerator, DrawDateGenerator drawDateGenerator, Clock clock) {
        this.clock = clock;
        this.idGenerator = idGenerator;
        this.drawDateGenerator = drawDateGenerator;

    }

    LotteryTicket generateTicket(Collection<Integer> numbers) {
        UUID id = idGenerator.generateId();
        LocalDateTime drawDate = drawDateGenerator.generateDrawDate(LocalDateTime.now(clock));
        return new LotteryTicket(id, new ArrayList<>(numbers), drawDate, TicketMessage.VALID.message);
    }

    LotteryTicket generateInvalidTicket(Collection<Integer> numbers) {
        return new LotteryTicket(null, new ArrayList<>(numbers), null, TicketMessage.INVALID.message);
    }
}
