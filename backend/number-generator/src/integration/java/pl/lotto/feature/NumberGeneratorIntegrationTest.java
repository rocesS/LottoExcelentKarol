package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.lotto.cron=*/2 * * * * *"
})
public class NumberGeneratorIntegrationTest extends BaseIntegrationTest {

    @Autowired
    public  NumbersGeneratorFacade numbersGeneratorFacade;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void should_return_400_status_code_when_winning_numbers_are_generated_and_proper_request() {
        //given
        LocalDateTime drawDate = LocalDateTime.now().with(next(SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(Durations.ONE_SECOND)
                .until(() -> numbersGeneratorFacade.retrieveWonNumbers(drawDate.toString()).winningNumbers() != null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);


        //when
        ResponseEntity<WinningNumbersDto> response = testRestTemplate.exchange(
                "/winningNumbers?request=" + drawDate, HttpMethod.GET, entity, WinningNumbersDto.class);


        //then
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}






