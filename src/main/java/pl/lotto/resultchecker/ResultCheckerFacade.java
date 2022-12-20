package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultchecker.dto.LotteryResultDto;

import java.util.Optional;

public class ResultCheckerFacade {
    NumberReceiverFacade numberReceiverFacade;
    NumbersGeneratorFacade numbersGeneratorFacade;
    HitNumbersChecker hitNumbersChecker;
    DTOMapper dtoMapper;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade, HitNumbersChecker hitNumbersChecker, DTOMapper dtoMapper) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
        this.hitNumbersChecker = hitNumbersChecker;
        this.dtoMapper = dtoMapper;
    }

    public Optional<LotteryResultDto> checkWinner(LotteryTicketDto lotteryTicketDto) {
        return Optional.ofNullable(dtoMapper.mapLotteryResultToDto(hitNumbersChecker.checkIfUserWon(lotteryTicketDto)));
    }
}
