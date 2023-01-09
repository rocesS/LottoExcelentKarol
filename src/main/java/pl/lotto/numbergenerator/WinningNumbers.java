package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

record WinningNumbers(UUID id, List<Integer> winningNumbers, LocalDateTime drawDate) {
}
