package pl.lotto.numbergenerator.dto;

import java.time.LocalDateTime;
import java.util.List;

public record WinningNumbersDto(List<Integer> winningNumbers, LocalDateTime drawDate) {
}

