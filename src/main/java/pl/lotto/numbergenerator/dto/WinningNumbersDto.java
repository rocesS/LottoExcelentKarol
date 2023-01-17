package pl.lotto.numbergenerator.dto;

import java.util.List;
import java.util.UUID;

public record WinningNumbersDto(UUID id, List<Integer> winningNumbers, String drawDate) {
}

