package pl.lotto.numbergenerator;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class NumbersGeneratorFacadeConfiguration {

    @Bean
    NumbersGenerator numbersGenerator() {
        return new NumbersGenerator();
    }

    @Bean
    DrawScheduler drawScheduler(NumbersGenerator numbersGenerator, WinningNumbersRepository winningNumbersRepository, Clock clock) {
        return new DrawScheduler(numbersGenerator, winningNumbersRepository, clock);
    }

    @Bean
    NumbersGeneratorFacade numbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        DrawDateChecker drawDateChecker = new DrawDateChecker(clock);
        WinningNumbersRetriever winningNumbersRetriever = new WinningNumbersRetriever(winningNumbersRepository, numbersGenerator(), drawDateChecker);
        return new NumbersGeneratorFacade(winningNumbersRetriever);
    }

    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        return numbersGeneratorFacade(winningNumbersRepository, clock);
    }
}
