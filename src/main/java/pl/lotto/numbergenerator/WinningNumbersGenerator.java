package pl.lotto.numbergenerator;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

class WinningNumbersGenerator {

    private final NumbersGenerator numbersGenerator;
    private final WinningNumbersRepository winningNumbersRepository;
    private final Clock clock;

    WinningNumbersGenerator(NumbersGenerator numbersGenerator, WinningNumbersRepository winningNumbersRepository, Clock clock) {
        this.numbersGenerator = numbersGenerator;
        this.winningNumbersRepository = winningNumbersRepository;
        this.clock = clock;
    }

    public boolean generateWinningNumbers() {
        List<Integer> numbers = numbersGenerator.generateNumbers();

        LocalDateTime drawDate = LocalDateTime.now(clock)
                .withHour(DrawTime.HOURS.getValue()).withMinute(DrawTime.MINUTES.getValue()).withSecond(DrawTime.SECONDS.getValue());

        winningNumbersRepository.insert(new WinningNumbers(numbers, drawDate));
        return true;
    }

}
