package pl.lotto.resultchecker.dto;

import java.util.List;

public record LotteryResultDto(String message, List<Integer> yourNumbers, List<Integer> winningNumbers, int hitNumbers) {}