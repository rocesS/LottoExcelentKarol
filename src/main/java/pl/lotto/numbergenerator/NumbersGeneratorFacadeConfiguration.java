package pl.lotto.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
class NumbersGeneratorFacadeConfiguration {
    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository, LocalDateTime localDateTime) {
        NumbersGenerator numbersGenerator = new NumbersGenerator();
        DrawDateChecker drawDateChecker = new DrawDateChecker(localDateTime);
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
