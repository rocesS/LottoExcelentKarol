package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;

interface WinningNumbersRepository {
    void addWinningNumbers(LocalDateTime drawDate, List<Integer> numbers);

    WinningNumbers getWinningNumbers(LocalDateTime drawDate);

}
