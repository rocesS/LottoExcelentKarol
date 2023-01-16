package pl.lotto.numbergenerator;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

class DrawScheduler {

    private final NumbersGenerator numbersGenerator;
    private final WinningNumbersRepository winningNumbersRepository;

    private final Clock clock;

    DrawScheduler(NumbersGenerator numbersGenerator, WinningNumbersRepository winningNumbersRepository, Clock clock) {
        this.numbersGenerator = numbersGenerator;
        this.winningNumbersRepository = winningNumbersRepository;
        this.clock = clock;
    }

    @Scheduled(cron = "${spring.lotto.cron}")
    public void scheduleTaskUsingCronExpression() {
        List<Integer> numbers = numbersGenerator.generateNumbers();

        LocalDateTime drawDate = LocalDateTime.now(clock)
                .withHour(DrawTime.HOURS.getValue()).withMinute(DrawTime.MINUTES.getValue()).withSecond(DrawTime.SECONDS.getValue());

        winningNumbersRepository.insert(new WinningNumbers(numbers, drawDate));
    }

}
