package pl.lotto.resultannouncer.dto;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public record LotteryAnnouncementDto(List<Integer> yourNumbers, List<Integer> winningNumbers,
                                     int hitNumbers, String message) {
}
