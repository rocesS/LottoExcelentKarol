package pl.lotto.resultchecker;

import java.util.List;

record LotteryResult(List<Integer> yourNumbers, List<Integer> winningNumbers,
                     int hitNumbers, String message) {
}
