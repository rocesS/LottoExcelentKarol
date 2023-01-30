package pl.lotto.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.lotto.infrastructure.http.ResultCheckerHttpClient;
import pl.lotto.numberreceiver.NumberReceiverFacade;

@Configuration
class ResultCheckerFacadeConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    RetrieverWonNumbersClient retrieverWonNumbersClientBean() {
        return new ResultCheckerHttpClient(restTemplate());
    }

    @Bean
    ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade) {
        HitNumbersChecker hitNumbersChecker = new HitNumbersChecker(numberReceiverFacade, retrieverWonNumbersClientBean());
        return new ResultCheckerFacade(numberReceiverFacade, hitNumbersChecker);
    }

    ResultCheckerFacade createFacadeForTest(NumberReceiverFacade numberReceiverFacade, RetrieverWonNumbersClient retrieverWonNumbersClient) {
        HitNumbersChecker hitNumbersChecker = new HitNumbersChecker(numberReceiverFacade, retrieverWonNumbersClient);
        return new ResultCheckerFacade(numberReceiverFacade, hitNumbersChecker);
    }
}
