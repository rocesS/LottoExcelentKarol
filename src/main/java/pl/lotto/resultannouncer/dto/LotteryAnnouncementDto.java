package pl.lotto.resultannouncer.dto;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public record LotteryAnnouncementDto(Optional<List<Integer>> yourNumbers, Optional<List<Integer>> winningNumbers,
                                     OptionalInt hitNumbers, String message) {
}
