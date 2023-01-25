package pl.lotto.numbergenerator.dto;

import java.util.List;

public record WinningNumbersDto(String id, List<Integer> winningNumbers, String drawDate) {
}

