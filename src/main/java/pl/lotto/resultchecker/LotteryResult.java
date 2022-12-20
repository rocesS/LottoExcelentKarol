package pl.lotto.resultchecker;

import java.util.List;

record LotteryResult(String message, List<Integer> yourNumbers, List<Integer> winningNumbers, int hitNumbers) {}
