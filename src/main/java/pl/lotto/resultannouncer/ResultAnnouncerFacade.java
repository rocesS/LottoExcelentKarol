package pl.lotto.resultannouncer;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;

import java.util.Optional;

public class ResultAnnouncerFacade {

    ResultCheckerFacade resultCheckerFacade;

    ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public Optional<LotteryAnnouncementDto> checkWinner(LotteryTicketDto lotteryTicketDto) {
        Optional<LotteryResultDto> lotteryResultDto = resultCheckerFacade.checkWinner(lotteryTicketDto);
        return lotteryResultDto.map(resultDto -> Optional.of(new LotteryAnnouncementDto(
                resultDto.message(), resultDto.yourNumbers(),
                resultDto.winningNumbers(), resultDto.hitNumbers()))).orElse(null);
    }
}
