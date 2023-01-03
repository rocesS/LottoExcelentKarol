package pl.lotto.resultchecker;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

record LotteryResult(Optional<List<Integer>> yourNumbers, Optional<List<Integer>> winningNumbers, OptionalInt hitNumbers, String message) {}
