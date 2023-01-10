package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

class TicketGenerator {

    IdGenerator idGenerator;
    DrawDateGenerator drawDateGenerator;

    TicketGenerator(IdGenerator idGenerator, DrawDateGenerator drawDateGenerator) {
        this.idGenerator = idGenerator;
        this.drawDateGenerator = drawDateGenerator;

    }

    LotteryTicket generateTicket(Collection<Integer> numbers) {
        UUID id = idGenerator.generateId();
        LocalDateTime drawDate = drawDateGenerator.generateDrawDate();
        return new LotteryTicket(id, new ArrayList<>(numbers), drawDate, "valid");
    }

    LotteryTicket generateInvalidTicket(Collection<Integer> numbers) {
        return new LotteryTicket(null, new ArrayList<>(numbers), null, "invalid");
    }
}
