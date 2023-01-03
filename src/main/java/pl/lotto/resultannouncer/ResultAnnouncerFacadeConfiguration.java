package pl.lotto.resultannouncer;

import pl.lotto.resultchecker.ResultCheckerFacade;


class ResultAnnouncerFacadeConfiguration {
    ResultAnnouncerFacade createFacadeForTest(ResultCheckerFacade resultCheckerFacade) {
        return new ResultAnnouncerFacade(resultCheckerFacade);
    }
}
