package pl.lotto.numbergenerator;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NumbersRetriever {
    WinningNumbersRepository winningNumbersRepository;
    NumbersGenerator numbersGenerator;
    DrawDateChecker drawDateChecker;

    public NumbersRetriever(WinningNumbersRepository winningNumbersRepo, NumbersGenerator numbersGenerator, DrawDateChecker drawDateChecker) {
        this.winningNumbersRepository = winningNumbersRepo;
        this.numbersGenerator = numbersGenerator;
        this.drawDateChecker = drawDateChecker;
    }

    public WinningNumbers retrieveWonNumbers(LocalDateTime drawDate) {
        WinningNumbers winningNumbers = winningNumbersRepository.getWinningNumbers(drawDate);
        boolean areWinningNumbersAvailable = winningNumbers.winningNumbers().isPresent();

        // if the draw has already taken place and winning numbers are in repo
        if (drawDateChecker.isDateAfterDraw(drawDate) && areWinningNumbersAvailable) {
            return winningNumbers;
        }
        // if the draw date is in the future and the draw has not yet taken place
        else if (drawDateChecker.isDateBeforeDraw(drawDate) && !areWinningNumbersAvailable) {
            List<Integer> numbers = numbersGenerator.generateNumbers();
            winningNumbersRepository.addWinningNumbers(drawDate, numbers);
            return winningNumbers;
        }
        // when the draw date has passed and there is no generated numbers, simply return null
        return new WinningNumbers(Optional.empty());
    }
}
