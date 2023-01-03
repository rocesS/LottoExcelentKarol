package pl.lotto.numberreceiver;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
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
        return new LotteryTicket(Optional.of(id), new ArrayList<>(numbers), Optional.of(drawDate), "valid");
    }

    LotteryTicket generateInvalidTicket(Collection<Integer> numbers) {
        return new LotteryTicket(Optional.empty(), new ArrayList<>(numbers), Optional.empty(), "invalid");
    }
}
