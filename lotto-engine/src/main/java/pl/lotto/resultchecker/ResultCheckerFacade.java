package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;

public class ResultCheckerFacade {
    NumberReceiverFacade numberReceiverFacade;
    HitNumbersChecker hitNumbersChecker;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, HitNumbersChecker hitNumbersChecker) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.hitNumbersChecker = hitNumbersChecker;
    }

    public LotteryResultDto checkWinner(String id) {
        return DtoMapper.mapLotteryResultToDto(hitNumbersChecker.checkIfUserWon(id));
    }
}
