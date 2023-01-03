package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;

class ResultCheckerFacadeConfiguration {
    ResultCheckerFacade createFacadeForTest(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade) {
        HitNumbersChecker hitNumbersChecker = new HitNumbersChecker(numberReceiverFacade, numbersGeneratorFacade);
        return new ResultCheckerFacade(numberReceiverFacade, numbersGeneratorFacade, hitNumbersChecker);
    }
}
