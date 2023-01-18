package pl.lotto.resultannouncer;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultAnnouncerFacadeTest {

    static ResultCheckerFacade resultCheckerFacade;
    static ResultAnnouncerFacade resultAnnouncerFacade;

    @BeforeAll
    static void init() {
        resultCheckerFacade = mock(ResultCheckerFacade.class);
        resultAnnouncerFacade = new ResultAnnouncerFacadeConfiguration().createFacadeForTest(resultCheckerFacade);
    }


    @Test
    void should_return_correct_lottery_results_with_winning_message_when_user_won() {
        //given
        LotteryResultDto lotteryResultDto = new LotteryResultDto(List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 6), 6, LotteryMessage.WIN.message);
        given(resultCheckerFacade.checkWinner(any(UUID.class)))
                .willReturn(lotteryResultDto);

        //when
        LotteryAnnouncementDto result = resultAnnouncerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.winningNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.hitNumbers()).isEqualTo(6),
                () -> assertThat(result.message()).isEqualTo(LotteryMessage.WIN.message));
    }

    @Test
    void should_return_correct_lottery_results_with_losing_message_when_user_lost() {
        //given
        LotteryResultDto lotteryResultDto = new LotteryResultDto(List.of(1, 2, 8, 9, 10, 11), List.of(1, 2, 3, 4, 5, 6), 2, LotteryMessage.LOSE.message);
        given(resultCheckerFacade.checkWinner(any(UUID.class))).willReturn(lotteryResultDto);

        //when
        LotteryAnnouncementDto result = resultAnnouncerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers()).isEqualTo(List.of(1, 2, 8, 9, 10, 11)),
                () -> assertThat(result.winningNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.hitNumbers()).isEqualTo(2),
                () -> assertThat(result.message()).isEqualTo(LotteryMessage.LOSE.message));
    }

    @Test
    void should_return_result_with_empty_credentials_and_invalid_message_when_user_gave_id_that_is_not_in_repo() {
        //given
        LotteryResultDto lotteryResultDto = new LotteryResultDto(null, null, 0, LotteryMessage.INVALID_ID.message);
        given(resultCheckerFacade.checkWinner(any(UUID.class))).willReturn(lotteryResultDto);

        //when
        LotteryAnnouncementDto result = resultAnnouncerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers()).isNull(),
                () -> assertThat(result.winningNumbers()).isNull(),
                () -> assertThat(result.hitNumbers()).isEqualTo(0),
                () -> assertThat(result.message()).isEqualTo(LotteryMessage.INVALID_ID.message));
    }
}




















