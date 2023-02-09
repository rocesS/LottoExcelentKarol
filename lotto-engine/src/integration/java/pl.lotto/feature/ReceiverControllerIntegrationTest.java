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
import pl.lotto.infrastructure.controller.numberreceiver.NumberReceiverRestController;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.TicketMessage;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.numberreceiver.dto.ReceiverRequestDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = NumberReceiverRestController.class)
public class ReceiverControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    NumberReceiverFacade numberReceiverFacade;


    @Test
    void should_return_400_status_code_when_list_has_not_proper_size() throws Exception {
        //given
        ReceiverRequestDto receiverRequest = new ReceiverRequestDto(List.of(1, 2, 3, 4, 5));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(receiverRequest));

        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //then
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }


    @Test
    void should_return_202_status_code_when_list_has_not_proper_elements_and_invalid_ticket_generated() throws Exception {
        //given
        ReceiverRequestDto receiverRequest = new ReceiverRequestDto(List.of(1, 2, 300, 4, 5, 6));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(receiverRequest));

        LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(null, new ArrayList<>(receiverRequest.numbers()),
                null, TicketMessage.INVALID.message);
        given(numberReceiverFacade.inputNumbers(receiverRequest.numbers())).willReturn(lotteryTicketDto);


        //when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();


        //then
        assertEquals(HttpStatus.ACCEPTED.value(),
                mvcResult.getResponse().getStatus(),
                "Incorrect HTTP Status Code returned");
    }
}
