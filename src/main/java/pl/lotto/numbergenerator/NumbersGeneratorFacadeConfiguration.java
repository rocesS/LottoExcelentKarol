package pl.lotto.numbergenerator;

import java.time.LocalDateTime;

class NumbersGeneratorFacadeConfiguration {
    NumbersGeneratorFacade createFacadeForTest(WinningNumbersRepository winningNumbersRepository, LocalDateTime localDateTime) {
        DTOMapper dtoMapper = new DTOMapper();
        NumbersGenerator numbersGenerator = new NumbersGenerator();
        DrawDateChecker drawDateChecker = new DrawDateChecker(localDateTime);
        return new NumbersGeneratorFacade(winningNumbersRepository, dtoMapper, numbersGenerator, drawDateChecker);
    }
}
