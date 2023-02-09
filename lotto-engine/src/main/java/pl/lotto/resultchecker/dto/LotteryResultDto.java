package pl.lotto.resultchecker.dto;

import java.util.List;

public record LotteryResultDto(List<Integer> yourNumbers, List<Integer> winningNumbers, int hitNumbers, String message) {
}