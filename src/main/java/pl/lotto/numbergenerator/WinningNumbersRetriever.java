package pl.lotto.numbergenerator;

import java.time.LocalDateTime;

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
        if (drawDateChecker.isAfterDraw(drawDate) && areWinningNumbersAvailable) {
            return winningNumbers;
        }

        return new WinningNumbers(null, drawDate);
    }
}
