package pl.lotto.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumbersGeneratorFacadeConfiguration {

    @Bean
    NumbersGenerator numbersGenerator() {
        return new NumbersGenerator();
    }

    @Bean
    WinningNumbersGenerator drawScheduler(NumbersGenerator numbersGenerator, WinningNumbersRepository winningNumbersRepository) {
        return new WinningNumbersGenerator(numbersGenerator, winningNumbersRepository);
    }

    @Bean
    NumbersGeneratorFacade numbersGeneratorFacade(WinningNumbersRepository winningNumbersRepository) {
        WinningNumbersRetriever winningNumbersRetriever = new WinningNumbersRetriever(winningNumbersRepository, numbersGenerator());
        WinningNumbersGenerator winningNumbersGenerator = new WinningNumbersGenerator(numbersGenerator(), winningNumbersRepository);
        return new NumbersGeneratorFacade(winningNumbersRetriever, winningNumbersGenerator);
    }

    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository) {
        return numbersGeneratorFacade(winningNumbersRepository);
    }
}
