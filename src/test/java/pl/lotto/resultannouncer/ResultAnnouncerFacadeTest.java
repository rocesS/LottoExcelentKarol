package pl.lotto.resultannouncer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultchecker.ResultCheckerFacade;
import pl.lotto.resultchecker.dto.LotteryResultDto;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultAnnouncerFacadeTest {

    static ResultCheckerFacade resultCheckerFacade;
    static ResultAnnouncerFacade resultAnnouncerFacade;
    static LotteryTicketDtoObjectTest lotteryTicketDtoObjectTest;

    @BeforeAll
    static void init() {
        resultCheckerFacade = mock(ResultCheckerFacade.class);
        resultAnnouncerFacade = new ResultAnnouncerFacadeConfiguration().createFacadeForTest(resultCheckerFacade);
        lotteryTicketDtoObjectTest = new LotteryTicketDtoObjectTest();
    }


    @Test
    void should_return_correct_lottery_results_with_winning_message_when_user_won() {
        //given
        Optional<LotteryResultDto> lotteryResultDto = Optional.of(
                new LotteryResultDto("you won!", List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6), 6));
        given(resultCheckerFacade.checkWinner(any(LotteryTicketDto.class))).willReturn(lotteryResultDto);

        //when
        Optional<LotteryAnnouncementDto> result = resultAnnouncerFacade.checkWinner(lotteryTicketDtoObjectTest.getLotteryTicketDto());

        //then
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.message()).isEqualTo("you won!"));
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.yourNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)));
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.winningNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)));
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.hitNumbers()).isEqualTo(6));
    }

    @Test
    void should_return_correct_lottery_results_with_losing_message_when_user_lost() {
        //given
        Optional<LotteryResultDto> lotteryResultDto = Optional.of(
                new LotteryResultDto("you lost!", List.of(1, 2, 8, 9, 10, 11), List.of(1, 2, 3, 4, 5, 6), 2));
        given(resultCheckerFacade.checkWinner(any(LotteryTicketDto.class))).willReturn(lotteryResultDto);

        //when
        Optional<LotteryAnnouncementDto> result = resultAnnouncerFacade.checkWinner(lotteryTicketDtoObjectTest.getLotteryTicketDto());

        //then
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.message()).isEqualTo("you lost!"));
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.yourNumbers()).isEqualTo(List.of(1, 2, 8, 9, 10, 11)));
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.winningNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)));
        result.ifPresent(lotteryAnnouncementDto -> assertThat(lotteryAnnouncementDto.hitNumbers()).isEqualTo(2));
    }
}