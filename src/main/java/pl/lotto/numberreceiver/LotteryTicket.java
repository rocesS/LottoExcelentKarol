package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

record LotteryTicket (Optional<UUID> id, List<Integer> numbers, Optional<LocalDateTime> drawDate, String message) {}

