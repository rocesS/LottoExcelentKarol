package pl.lotto.resultannouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.resultchecker.ResultCheckerFacade;

@Configuration
class ResultAnnouncerFacadeConfiguration {

    @Bean
    ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade) {
        return new ResultAnnouncerFacade(resultCheckerFacade);
    }

    ResultAnnouncerFacade createFacadeForTest(ResultCheckerFacade resultCheckerFacade) {
        return resultAnnouncerFacade(resultCheckerFacade);
    }
}
