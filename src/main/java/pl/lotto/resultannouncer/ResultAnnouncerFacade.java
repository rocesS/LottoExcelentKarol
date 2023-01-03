package pl.lotto.resultannouncer;

import org.springframework.stereotype.Service;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;

import java.util.UUID;

@Service
public class ResultAnnouncerFacade {

    ResultCheckerFacade resultCheckerFacade;

    ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public LotteryAnnouncementDto checkWinner(UUID id) {
        LotteryResultDto lotteryResultDto = resultCheckerFacade.checkWinner(id);
        return new LotteryAnnouncementDto(lotteryResultDto.yourNumbers(), lotteryResultDto.winningNumbers(),
                lotteryResultDto.hitNumbers(), lotteryResultDto.message());
    }
}
