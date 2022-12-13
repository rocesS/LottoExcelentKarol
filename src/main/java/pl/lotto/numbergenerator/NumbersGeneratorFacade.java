package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;

public class NumbersGeneratorFacade {
    WinningNumbersRepo winningNumbersRepo;

    NumbersGeneratorFacade(WinningNumbersRepo winningNumbersRepo) {
        this.winningNumbersRepo = winningNumbersRepo;
    }

    public WinningNumbersDto retrieveWonNumbers(LocalDateTime drawDate) {
        return winningNumbersRepo.getWinningNumbers(drawDate);
    }
}
