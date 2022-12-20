package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class NumbersGeneratorFacade {
    WinningNumbersRepository winningNumbersRepository;
    DTOMapper dtoMapper;
    NumbersGenerator numbersGenerator;
    DrawDateChecker drawDateChecker;

    public NumbersGeneratorFacade(WinningNumbersRepository winningNumbersRepo, DTOMapper dtoMapper, NumbersGenerator numbersGenerator, DrawDateChecker drawDateChecker) {
        this.winningNumbersRepository = winningNumbersRepo;
        this.dtoMapper = dtoMapper;
        this.numbersGenerator = numbersGenerator;
        this.drawDateChecker = drawDateChecker;
    }

    public Optional<WinningNumbersDto> retrieveWonNumbers(LocalDateTime drawDate) {
        // if the draw has already taken place and winning numbers are in repo
        if(drawDateChecker.isDateAfterDraw(drawDate) && winningNumbersRepository.getWinningNumbers(drawDate).winningNumbers() != null) {
            return Optional.of(dtoMapper.mapWinningNumbersToDto(winningNumbersRepository.getWinningNumbers(drawDate)));
        }
        // if the draw date is in the future and the draw has not yet taken place
        else if(drawDateChecker.isDateBeforeDraw(drawDate) && winningNumbersRepository.getWinningNumbers(drawDate) == null) {
            List<Integer> numbers = numbersGenerator.generateNumbers();
            winningNumbersRepository.addWinningNumbers(drawDate, numbers);
            return Optional.empty();
        }
        // when the draw date has passed and there is no generated numbers, simply return null
        return Optional.empty();
    }
}
