package pl.lotto.numbergenerator;

import org.springframework.scheduling.annotation.Scheduled;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;


public class NumbersGeneratorFacade {
    private final WinningNumbersRetriever winningNumbersRetriever;
    private final WinningNumbersGenerator winningNumbersGenerator;

    public NumbersGeneratorFacade(WinningNumbersRetriever winningNumbersRetriever, WinningNumbersGenerator winningNumbersGenerator) {
        this.winningNumbersRetriever = winningNumbersRetriever;
        this.winningNumbersGenerator = winningNumbersGenerator;
    }

    public WinningNumbersDto retrieveWonNumbers(String drawDate) {
        WinningNumbers winningNumbers = winningNumbersRetriever.retrieveWonNumbers(drawDate);
        return DtoMapper.mapWinningNumbersToDto(winningNumbers);
    }

    @Scheduled(cron = "${spring.lotto.cron}")
    public void scheduledDraw() {
        winningNumbersGenerator.generateWinningNumbers();
    }
}
