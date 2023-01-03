package pl.lotto.numbergenerator.dto;

import java.util.List;
import java.util.Optional;

public record WinningNumbersDto(Optional<List<Integer>> winningNumbers) {}

