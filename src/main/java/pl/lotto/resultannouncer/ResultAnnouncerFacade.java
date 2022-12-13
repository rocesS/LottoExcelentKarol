package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.ResultAnnouncerDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.ResultCheckerDto;

import java.util.UUID;

public class ResultAnnouncerFacade {
    ResultCheckerFacade resultCheckerFacade;

    ResultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        this.resultCheckerFacade = resultCheckerFacade;
    }

    public ResultAnnouncerDto checkWinner(UUID id) {
        ResultCheckerDto resultCheckerDto = resultCheckerFacade.checkWinner(id);
        return new ResultAnnouncerDto(resultCheckerDto.getMessage());
    }
}
