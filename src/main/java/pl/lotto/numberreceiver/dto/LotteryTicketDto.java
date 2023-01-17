package pl.lotto.numberreceiver.dto;

import java.util.List;
import java.util.UUID;

public record LotteryTicketDto(UUID id, List<Integer> numbers, String drawDate, String message) {

}
