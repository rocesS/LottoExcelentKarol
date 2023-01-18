package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.WinningNumbersRepository;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.ReceiverRequestDto;
import pl.lotto.resultannouncer.dto.AnnouncerRequestDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.lotto.cron=*/2 * * * * *"
})
public class WinningUserLottoV2IntegrationTest extends BaseIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    NumbersGeneratorFacade numbersGeneratorFacade;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WinningNumbersRepository winningNumbersRepository;


    @Test
    public void f() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        ReceiverRequestDto receiverRequest = new ReceiverRequestDto(List.of(1, 2, 3, 4, 5, 6));

        String receiverJson = objectMapper.writeValueAsString(receiverRequest);

        HttpEntity<String> receiverHttpEntity = new HttpEntity<>(receiverJson, headers);


        ResponseEntity<LotteryTicketDto> receiverResponseEntity = testRestTemplate.postForEntity("/numbers",
                receiverHttpEntity,
                LotteryTicketDto.class);
        LotteryTicketDto lotteryTicketDto = receiverResponseEntity.getBody();


        assertEquals(HttpStatus.ACCEPTED, receiverResponseEntity.getStatusCode());







        LocalDateTime drawDate = LocalDateTime.now().with(next(SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(Durations.ONE_SECOND)
                .until(() -> numbersGeneratorFacade.retrieveWonNumbers(drawDate.toString()).winningNumbers() != null);






        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        AnnouncerRequestDto announcerRequest = new AnnouncerRequestDto(lotteryTicketDto.id());

        String announcerJson = objectMapper.writeValueAsString(announcerRequest);

        HttpEntity<String> announcerHttpEntity = new HttpEntity<>(announcerJson, headers);


        ResponseEntity<LotteryAnnouncementDto> announcerResponseEntity = testRestTemplate.postForEntity("/announcement",
                announcerHttpEntity,
                LotteryAnnouncementDto.class);


        assertEquals(HttpStatus.ACCEPTED, announcerResponseEntity.getStatusCode());
    }
}