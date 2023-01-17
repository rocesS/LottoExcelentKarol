package pl.lotto.resultchecker;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

record LotteryResult(List<Integer> yourNumbers, List<Integer> winningNumbers,
                     int hitNumbers, String message) {
}
