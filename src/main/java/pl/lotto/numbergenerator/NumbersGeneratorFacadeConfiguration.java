package pl.lotto.numbergenerator;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumbersGeneratorFacadeConfiguration {

    @Bean
    NumbersGenerator numbersGenerator() {
        return new NumbersGenerator();
    }

    @Bean
    WinningNumbersGenerator drawScheduler(NumbersGenerator numbersGenerator, WinningNumbersRepository winningNumbersRepository, Clock clock) {
        return new WinningNumbersGenerator(numbersGenerator, winningNumbersRepository, clock);
    }

    @Bean
    NumbersGeneratorFacade numbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        WinningNumbersRetriever winningNumbersRetriever = new WinningNumbersRetriever(winningNumbersRepository, numbersGenerator());
        WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator(numbersGenerator(), winningNumbersRepository, clock);
        return new NumbersGeneratorFacade(winningNumbersRetriever, winningNumbersGenerator);
    }

    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository, Clock clock) {
        return numbersGeneratorFacade(winningNumbersRepository, clock);
    }
}
