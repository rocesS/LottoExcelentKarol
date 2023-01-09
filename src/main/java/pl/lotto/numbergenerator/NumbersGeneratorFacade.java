package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;

public class NumbersGeneratorFacade {
    private final WinningNumbersRetriever winningNumbersRetriever;

    public NumbersGeneratorFacade(WinningNumbersRetriever winningNumbersRetriever) {
        this.winningNumbersRetriever = winningNumbersRetriever;
    }

    public WinningNumbersDto retrieveWonNumbers(LocalDateTime drawDate) {
        return DtoMapper.mapWinningNumbersToDto(winningNumbersRetriever.retrieveWonNumbers(drawDate));
    }
}
