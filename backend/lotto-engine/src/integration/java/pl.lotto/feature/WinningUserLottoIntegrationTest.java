package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinningUserLottoIntegrationTest extends BaseIntegrationTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void user_should_get_ticket_and_announcement() throws Exception {
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

        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/winningNumbers"))
                .withQueryParam("request", WireMock.equalTo(drawDate.toString()))
                .willReturn(aResponse()
                        .withBody(objectMapper.writeValueAsString(winningNumbersDto))
                        .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.OK.value())));

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        AnnouncerRequestDto announcerRequest = new AnnouncerRequestDto(lotteryTicketDto.id());
        String announcerJson = objectMapper.writeValueAsString(announcerRequest);

        HttpEntity<String> announcerHttpEntity = new HttpEntity<>(announcerJson, headers);


        //when
        ResponseEntity<LotteryAnnouncementDto> announcerResponseEntity = testRestTemplate.postForEntity("/announcement",
                announcerHttpEntity,
                LotteryAnnouncementDto.class);


        //then
        assertEquals(HttpStatus.ACCEPTED, announcerResponseEntity.getStatusCode());
    }
}
