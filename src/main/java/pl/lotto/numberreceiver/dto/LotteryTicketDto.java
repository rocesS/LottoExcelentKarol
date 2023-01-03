package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record LotteryTicketDto(Optional<UUID> id, List<Integer> numbers, Optional<LocalDateTime> drawDate, String message) {
}
