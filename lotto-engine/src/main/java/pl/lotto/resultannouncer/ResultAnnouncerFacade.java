package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;

public class ResultAnnouncerFacade {
    ResultCheckerFacade resultCheckerFacade;

    ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public LotteryAnnouncementDto checkWinner(String id) {
        LotteryResultDto lotteryResultDto = resultCheckerFacade.checkWinner(id);
        return new LotteryAnnouncementDto(lotteryResultDto.yourNumbers(), lotteryResultDto.winningNumbers(),
                lotteryResultDto.hitNumbers(), lotteryResultDto.message());
    }

    public boolean isValidUUID(String uuid) {
        return UuidValidator.isValidUUID(uuid);
    }
}
