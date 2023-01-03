package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WinningNumbersRepositoryTest implements WinningNumbersRepository {
    Map<LocalDateTime, List<Integer>> winningNumbers = new HashMap<>();

    @Override
    public void addWinningNumbers(LocalDateTime drawDate, List<Integer> numbers) {
        winningNumbers.put(drawDate, numbers);
    }

    @Override
    public WinningNumbers getWinningNumbers(LocalDateTime drawDate) {
        return new WinningNumbers(Optional.of(winningNumbers.get(drawDate)));
    }
}
