package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.ReceiverRequestDto;
import pl.lotto.resultannouncer.dto.AnnouncerRequestDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultchecker.dto.WinningNumbersDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.lotto.cron=*/2 * * * * *",
        "name.service.url=http://localhost:8000"
})
public class WinningUserLottoIntegrationTest extends BaseIntegrationTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private WireMockServer wireMockServer;

    @Value("${name.service.url}")
    private String NumberGeneratorServiceUrl;

    @BeforeEach
    void init() {
        wireMockServer = new WireMockServer();
        //WireMock.configureFor("localhost", 8000);
        wireMockServer.start();
    }


    @Test
    public void f() throws Exception {
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ReceiverRequestDto receiverRequest = new ReceiverRequestDto(List.of(1, 2, 3, 4, 5, 6));

        String receiverJson = objectMapper.writeValueAsString(receiverRequest);

        HttpEntity<String> receiverHttpEntity = new HttpEntity<>(receiverJson, headers);


        //when
        ResponseEntity<LotteryTicketDto> receiverResponseEntity = testRestTemplate.postForEntity("/numbers",
                receiverHttpEntity,
                LotteryTicketDto.class);
        LotteryTicketDto lotteryTicketDto = receiverResponseEntity.getBody();


        //then
        assertEquals(HttpStatus.ACCEPTED, receiverResponseEntity.getStatusCode());





        //given
        LocalDateTime drawDate = LocalDateTime.now().with(next(SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(UUID.randomUUID().toString(), List.of(1, 2, 3, 4, 5, 6), drawDate.toString());

        stubFor(WireMock.get(urlMatching(NumberGeneratorServiceUrl + "/winningNumbers?request=" + drawDate))
                .willReturn(aResponse()
                        .withBody(objectMapper.writeValueAsString(winningNumbersDto))
                        .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.OK.value())));


        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> announcerHttpEntity = new HttpEntity<>(headers);


        //when
        ResponseEntity<LotteryAnnouncementDto> announcerResponseEntity = testRestTemplate.exchange("/announcement?request=" + lotteryTicketDto.id(),
                HttpMethod.GET,
                announcerHttpEntity,
                LotteryAnnouncementDto.class);


        //then
        assertEquals(HttpStatus.ACCEPTED, announcerResponseEntity.getStatusCode());
    }
}




