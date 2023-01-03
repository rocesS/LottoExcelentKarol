package pl.lotto.resultchecker;

import org.springframework.stereotype.Service;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;
import java.util.UUID;

@Service
public class ResultCheckerFacade {
    NumberReceiverFacade numberReceiverFacade;
    NumbersGeneratorFacade numbersGeneratorFacade;
    HitNumbersChecker hitNumbersChecker;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade, HitNumbersChecker hitNumbersChecker) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
        this.hitNumbersChecker = hitNumbersChecker;
    }

    public LotteryResultDto checkWinner(UUID id) {
        return DtoMapper.mapLotteryResultToDto(hitNumbersChecker.checkIfUserWon(id));
    }
}
