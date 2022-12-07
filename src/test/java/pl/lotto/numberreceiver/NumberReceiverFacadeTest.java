package pl.lotto.numberreceiver;

import java.util.List;
import org.junit.jupiter.api.Test;

public class NumberReceiverFacadeTest {

    @Test
    public void should_return_result_with_lottery_id_and_draw_date_when_user_gave_six_numbers_in_range_of_1_to_99(){
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade();
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        // when
//        InputNumbersDto result = numberReceiverFacade.inputNumbers(numbersFromUser);
//        // then
//        assertThat(result)
    }

    @Test
    public void should_return_result_without_lottery_id_and_without_draw_date_when_user_gave_less_than_six_numbers(){

    }

    @Test
    public void should_return_result_without_lottery_id_and_without_draw_date_when_user_gave_more_than_six_numbers(){

    }

    @Test
    public void should_return_result_without_lottery_id_and_without_draw_date_when_user_gave_atleast_one_duplicate(){

    }
}
