package pl.lotto.numberreceiver.dto;

import java.util.List;

public record LotteryTicketDto(String id, List<Integer> numbers, String drawDate, String message) {
}
