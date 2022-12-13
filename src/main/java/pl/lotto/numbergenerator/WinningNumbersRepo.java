package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningNumbersRepo {
    Map<LocalDateTime, List<Integer>> winningNumbers = new HashMap<>();

    void addWinningNumbers(LocalDateTime drawDate, List<Integer> numbers) {
        winningNumbers.put(drawDate, numbers);
    }

    WinningNumbersDto getWinningNumbers(LocalDateTime drawDate) {
        List<Integer> numbers = winningNumbers.get(drawDate);
        if(numbers == null) {
            return new WinningNumbersDto(false, null);
        }
        else {
            return new WinningNumbersDto(true, numbers);
        }
    }
}
