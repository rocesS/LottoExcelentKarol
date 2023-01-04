package pl.lotto.numbergenerator;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
class NumbersGeneratorFacadeConfiguration {

    @Bean
    NumbersGeneratorFacade numbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        NumbersGenerator numbersGenerator = new NumbersGenerator();
        DrawDateChecker drawDateChecker = new DrawDateChecker(LocalDateTime.now(clock));
        return new NumbersGeneratorFacade(new NumbersRetriever(winningNumbersRepository, numbersGenerator, drawDateChecker));
    }

    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        NumbersGenerator numbersGenerator = new NumbersGenerator();
        DrawDateChecker drawDateChecker = new DrawDateChecker(LocalDateTime.now(clock));
        return new NumbersGeneratorFacade(new NumbersRetriever(winningNumbersRepository, numbersGenerator, drawDateChecker));
    }

    @Bean
    WinningNumbersRepository winningNumbersRepository() {
        return new WinningNumbersRepository() {
            @Override
            public void addWinningNumbers(LocalDateTime drawDate, List<Integer> numbers) {

            }

            @Override
            public WinningNumbers getWinningNumbers(LocalDateTime drawDate) {
                return null;
            }

        };
    }
}
