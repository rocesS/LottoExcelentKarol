package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import pl.lotto.LottoApplication;
import pl.lotto.resultannouncer.LotteryMessage;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = LottoApplication.class)
public class AnnouncerControllerIntegrationTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    ResultAnnouncerFacade resultAnnouncerFacade;

    @Test
    void should_return_400_status_code_when_invalid_uuid_provided() {
        //given
        String invalidUUID = "792g51-237c-11ed-861d-024acz20002";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);


        //when
        ResponseEntity<LotteryAnnouncementDto> response = testRestTemplate.exchange(
                "/announcement/" + invalidUUID, HttpMethod.GET, entity, LotteryAnnouncementDto.class);


        //then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void should_return_202_status_code_when_valid_ticket_provided_and_no_draw() {
        //given
        String validUUID = UUID.randomUUID().toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        LotteryAnnouncementDto lotteryAnnouncementDto = new LotteryAnnouncementDto(null, null, 0, LotteryMessage.NO_DRAW.message);
        given(resultAnnouncerFacade.checkWinner(any(String.class))).willReturn(lotteryAnnouncementDto);

        given(resultAnnouncerFacade.isValidUUID(validUUID)).willReturn(true);


        //then
        ResponseEntity<LotteryAnnouncementDto> response = testRestTemplate.exchange(
                "/announcement/" + validUUID, HttpMethod.GET, entity, LotteryAnnouncementDto.class);


        //then
        verify(resultAnnouncerFacade, times(1)).checkWinner(validUUID);
        assertEquals(HttpStatus.ACCEPTED,
                response.getStatusCode(),
                "Incorrect HTTP Status Code returned");
    }
}
