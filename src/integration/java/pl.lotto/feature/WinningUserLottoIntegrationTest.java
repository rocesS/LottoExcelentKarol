package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.shaded.org.awaitility.Durations;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.ReceiverRequestDto;
import pl.lotto.resultannouncer.dto.AnnouncerRequestDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.lotto.cron=*/1 * * * * *"
})
public class WinningUserLottoIntegrationTest extends BaseIntegrationTest {

    @Autowired
    NumbersGeneratorFacade numbersGeneratorFacade;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void f() throws Exception {


        ReceiverRequestDto receiverRequest = new ReceiverRequestDto(List.of(1, 2, 3, 4, 5, 6));


        RequestBuilder receiverRequestBuilder = MockMvcRequestBuilders.post("/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(receiverRequest));


        MvcResult mvcResult = mockMvc.perform(receiverRequestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        LotteryTicketDto lotteryTicketDto = objectMapper.readValue(responseBodyAsString, LotteryTicketDto.class);


/*
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
*/
        //String content = result.getResponse().getContentAsString();






                Awaitility.await()
                        .atMost(10, TimeUnit.SECONDS)
                        .pollInterval(Durations.ONE_SECOND)
                        .until(() -> numbersGeneratorFacade.drawWinningNumbers());

        System.out.println("udalo sie");

        UUID id = lotteryTicketDto.id();

        AnnouncerRequestDto announcerRequest = new AnnouncerRequestDto(id);

        RequestBuilder announcerRequestBuilder = MockMvcRequestBuilders.post("/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(announcerRequest));


        MvcResult result = mockMvc.perform(announcerRequestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString1 = mvcResult.getResponse().getContentAsString();
        LotteryAnnouncementDto lotteryAnnouncementDto = objectMapper.readValue(responseBodyAsString1, LotteryAnnouncementDto.class);

        assertEquals(lotteryAnnouncementDto.yourNumbers(), lotteryTicketDto.numbers());



    }
}
