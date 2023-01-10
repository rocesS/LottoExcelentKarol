package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;

class WinningNumbersRetriever {
    WinningNumbersRepository winningNumbersRepository;
    NumbersGenerator numbersGenerator;
    DrawDateChecker drawDateChecker;

    public WinningNumbersRetriever(WinningNumbersRepository winningNumbersRepo, NumbersGenerator numbersGenerator, DrawDateChecker drawDateChecker) {
        this.winningNumbersRepository = winningNumbersRepo;
        this.numbersGenerator = numbersGenerator;
        this.drawDateChecker = drawDateChecker;
    }

    public WinningNumbers retrieveWonNumbers(LocalDateTime drawDate) {
        WinningNumbers winningNumbers = winningNumbersRepository.findByDrawDate(drawDate);
        boolean areWinningNumbersAvailable = winningNumbers != null;

        // if the draw has already taken place and winning numbers are in repo
        if (drawDateChecker.isDateAfterDraw(drawDate) && areWinningNumbersAvailable) {
            return winningNumbers;
        }
        // if the draw date is in the future and the draw has not yet taken place
        else if (drawDateChecker.isDateBeforeDraw(drawDate) && !areWinningNumbersAvailable) {
            List<Integer> numbers = numbersGenerator.generateNumbers();
            winningNumbersRepository.insert(new WinningNumbers(numbers, drawDate));
            return null;
        }
        // when the draw date has passed and there is no generated numbers, simply return null
        return new WinningNumbers(null, drawDate);
    }
}
