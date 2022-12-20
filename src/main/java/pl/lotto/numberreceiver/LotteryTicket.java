package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

record LotteryTicket (UUID id, List<Integer> numbers, LocalDateTime drawDate) {}

