package pl.lotto.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.lotto.infrastructure.controller.resultannouncer.ResultAnnouncerRestController;
import pl.lotto.resultannouncer.LotteryMessage;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(controllers = ResultAnnouncerRestController.class)
public class AnnouncerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ResultAnnouncerFacade resultAnnouncerFacade;




    @Test
    void should_return_400_status_code_when_invalid_uuid_provided() throws Exception {
        //given
        String invalidUUID = "792g51-237c-11ed-861d-024acz20002";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(invalidUUID);

        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //then
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }


    @Test
    void should_return_202_status_code_when_valid_ticket_provided_and_no_draw() throws Exception {
        //given
        UUID validUUID = UUID.randomUUID();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/announcement")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validUUID));


        LotteryAnnouncementDto lotteryAnnouncementDto = new LotteryAnnouncementDto(null, null, 0, LotteryMessage.NO_DRAW.message);
        given(resultAnnouncerFacade.checkWinner(any(String.class))).willReturn(lotteryAnnouncementDto);

        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //then
        verify(resultAnnouncerFacade, times(1)).checkWinner(validUUID.toString());
        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }
}
