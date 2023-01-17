package pl.lotto.resultchecker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultchecker.dto.LotteryResultDto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultCheckerFacadeTest {

    static NumberReceiverFacade numberReceiverFacade;
    static NumbersGeneratorFacade numbersGeneratorFacade;
    static ResultCheckerFacade resultCheckerFacade;
    static LocalDateTime randomDate;
    static LocalDateTime drawDate;
    static String randomDateString;

    @BeforeAll
    static void init() {
        numberReceiverFacade = mock(NumberReceiverFacade.class);
        numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        resultCheckerFacade = new ResultCheckerFacadeConfiguration().createFacadeForTest(numberReceiverFacade, numbersGeneratorFacade);
        randomDate = LocalDateTime.of(2017, 2, 13, 15, 56);
        drawDate = randomDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
        randomDateString = randomDate.toString();
    }

    @Test
    void should_return_correct_lottery_results_with_winning_message_when_user_won() {
        //given
        LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(UUID.randomUUID(), (List.of(1, 2, 3, 4, 5, 6)),
                randomDateString, "valid");
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(UUID.randomUUID(), List.of(1, 2, 3, 4, 5, 6), randomDateString);

        given(numberReceiverFacade.retrieveUserNumbers(any(UUID.class))).willReturn(lotteryTicketDto);
        given(numbersGeneratorFacade.retrieveWonNumbers(randomDateString)).willReturn(winningNumbersDto);

        //when
        LotteryResultDto result = resultCheckerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.winningNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.hitNumbers()).isEqualTo(6),
                () -> assertThat(result.message()).isEqualTo(LotteryMessage.WIN.message));
    }

    @Test
    void should_return_correct_lottery_results_with_losing_message_when_user_lost() {
        //given
        LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(UUID.randomUUID(), (List.of(1, 2, 8, 9, 10, 11)),
                randomDateString, "valid");
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(UUID.randomUUID(), List.of(1, 2, 3, 4, 5, 6), randomDateString);

        given(numberReceiverFacade.retrieveUserNumbers(any(UUID.class))).willReturn(lotteryTicketDto);
        given(numbersGeneratorFacade.retrieveWonNumbers(randomDateString)).willReturn(winningNumbersDto);

        //when
        LotteryResultDto result = resultCheckerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers()).isEqualTo(List.of(1, 2, 8, 9, 10, 11)),
                () -> assertThat(result.winningNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.hitNumbers()).isEqualTo(2),
                () -> assertThat(result.message()).isEqualTo(LotteryMessage.LOSE.message));
    }

    @Test
    void should_return_result_with_empty_credentials_and_invalid_message_when_user_gave_invalid_id() {
        //given
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(UUID.randomUUID(), List.of(1, 2, 3, 4, 5, 6), randomDateString);

        given(numberReceiverFacade.retrieveUserNumbers(any(UUID.class))).willReturn(null);
        given(numbersGeneratorFacade.retrieveWonNumbers(randomDateString)).willReturn(winningNumbersDto);

        //when
        LotteryResultDto result = resultCheckerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers()).isNull(),
                () -> assertThat(result.winningNumbers()).isNull(),
                () -> assertThat(result.hitNumbers()).isEqualTo(0),
                () -> assertThat(result.message()).isEqualTo(LotteryMessage.INVALID_ID.message));
    }
}