package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
class ResultCheckerFacadeConfiguration {

    @Bean
    ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade) {
        HitNumbersChecker hitNumbersChecker = new HitNumbersChecker(numberReceiverFacade, numbersGeneratorFacade);
        return new ResultCheckerFacade(numberReceiverFacade, numbersGeneratorFacade, hitNumbersChecker);
    }

    ResultCheckerFacade createFacadeForTest(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade) {
        HitNumbersChecker hitNumbersChecker = new HitNumbersChecker(numberReceiverFacade, numbersGeneratorFacade);
        return new ResultCheckerFacade(numberReceiverFacade, numbersGeneratorFacade, hitNumbersChecker);
    }
}
