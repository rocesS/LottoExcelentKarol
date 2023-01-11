package pl.lotto.numbergenerator;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class NumbersGeneratorFacadeConfiguration {

    @Bean
    NumbersGeneratorFacade numbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        NumbersGenerator numbersGenerator = new NumbersGenerator();
        DrawDateChecker drawDateChecker = new DrawDateChecker(LocalDateTime.now(clock));
        WinningNumbersRetriever winningNumbersRetriever = new WinningNumbersRetriever(winningNumbersRepository, numbersGenerator, drawDateChecker);
        return new NumbersGeneratorFacade(winningNumbersRetriever);
    }

    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        return numbersGeneratorFacade(winningNumbersRepository, clock);
    }
}
