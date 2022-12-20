package pl.lotto.resultannouncer.dto;

import java.util.List;

public record LotteryAnnouncementDto(String message, List<Integer> yourNumbers, List<Integer> winningNumbers, int hitNumbers) {}
