package pl.lotto.numbergenerator.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record WinningNumbersDto(Optional<UUID> id, Optional<List<Integer>> winningNumbers, LocalDateTime drawDate) {
}

