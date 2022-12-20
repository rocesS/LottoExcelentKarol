package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

interface WinningNumbersRepository {
    void addWinningNumbers(LocalDateTime drawDate, List<Integer> numbers);

    WinningNumbers getWinningNumbers(LocalDateTime drawDate);
}
