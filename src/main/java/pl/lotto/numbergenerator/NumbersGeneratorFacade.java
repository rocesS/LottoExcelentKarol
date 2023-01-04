package pl.lotto.numbergenerator;


import org.springframework.stereotype.Service;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;

public class NumbersGeneratorFacade {
    private final NumbersRetriever numbersRetriever;

    public NumbersGeneratorFacade(NumbersRetriever numbersRetriever) {
        this.numbersRetriever = numbersRetriever;
    }

    public WinningNumbersDto retrieveWonNumbers(LocalDateTime drawDate) {
        return DtoMapper.mapWinningNumbersToDto(numbersRetriever.retrieveWonNumbers(drawDate));
    }
}
