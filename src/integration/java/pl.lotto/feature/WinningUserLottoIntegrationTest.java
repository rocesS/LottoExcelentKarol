package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.WinningNumbersRepository;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.ReceiverRequestDto;
import pl.lotto.resultannouncer.dto.AnnouncerRequestDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WinningUserLottoIntegrationTest extends BaseIntegrationTest {

    @Autowired
    NumbersGeneratorFacade numbersGeneratorFacade;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WinningNumbersRepository winningNumbersRepository;


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




        LocalDateTime drawDate = LocalDateTime.now().with(next(SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);

        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS)
                .pollInterval(Durations.ONE_SECOND)
                .until(() -> numbersGeneratorFacade.retrieveWonNumbers(drawDate.toString()).winningNumbers() != null);




        AnnouncerRequestDto announcerRequest = new AnnouncerRequestDto(lotteryTicketDto.id());

        RequestBuilder announcerRequestBuilder = MockMvcRequestBuilders.post("/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(announcerRequest));

        MvcResult result = mockMvc.perform(announcerRequestBuilder).andExpect(status().isAccepted()).andReturn();
        String responseBodyAsString1 = result.getResponse().getContentAsString();
        LotteryAnnouncementDto lotteryAnnouncementDto = objectMapper.readValue(responseBodyAsString1, LotteryAnnouncementDto.class);


        assertEquals(lotteryAnnouncementDto.yourNumbers(), lotteryTicketDto.numbers());
    }
}
