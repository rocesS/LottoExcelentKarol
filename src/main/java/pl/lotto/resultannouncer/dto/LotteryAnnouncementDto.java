package pl.lotto.resultannouncer.dto;

import java.util.List;

public record LotteryAnnouncementDto(List<Integer> yourNumbers, List<Integer> winningNumbers,
                                     int hitNumbers, String message) {
}
