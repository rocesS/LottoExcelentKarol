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
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ResultCheckerFacadeTest {

    static NumberReceiverFacade numberReceiverFacade;
    static NumbersGeneratorFacade numbersGeneratorFacade;
    static ResultCheckerFacade resultCheckerFacade;
    static LocalDateTime randomDate;
    static LocalDateTime drawDate;

    @BeforeAll
    static void init() {
        numberReceiverFacade = mock(NumberReceiverFacade.class);
        numbersGeneratorFacade = mock(NumbersGeneratorFacade.class);
        resultCheckerFacade = new ResultCheckerFacadeConfiguration().createFacadeForTest(numberReceiverFacade, numbersGeneratorFacade);
        randomDate = LocalDateTime.of(2017, 2, 13, 15, 56);
        drawDate = randomDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
    }

    @Test
    void should_return_correct_lottery_results_with_winning_message_when_user_won() {
        //given
        LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(Optional.of(UUID.randomUUID()), (List.of(1, 2, 3, 4, 5, 6)),
                Optional.of(randomDate), "valid");
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(Optional.of(UUID.randomUUID()), Optional.of(List.of(1, 2, 3, 4, 5, 6)), randomDate);

        given(numberReceiverFacade.retrieveUserNumbers(any(UUID.class))).willReturn(lotteryTicketDto);
        given(numbersGeneratorFacade.retrieveWonNumbers(randomDate)).willReturn(winningNumbersDto);

        //when
        LotteryResultDto result = resultCheckerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers().get()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.winningNumbers().get()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.hitNumbers().getAsInt()).isEqualTo(6),
                () -> assertThat(result.message()).isEqualTo("you won!"));
    }

    @Test
    void should_return_correct_lottery_results_with_losing_message_when_user_lost() {
        //given
        LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(Optional.of(UUID.randomUUID()), (List.of(1, 2, 8, 9, 10, 11)),
                Optional.of(randomDate), "valid");
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(Optional.of(UUID.randomUUID()), Optional.of(List.of(1, 2, 3, 4, 5, 6)), randomDate);

        given(numberReceiverFacade.retrieveUserNumbers(any(UUID.class))).willReturn(lotteryTicketDto);
        given(numbersGeneratorFacade.retrieveWonNumbers(randomDate)).willReturn(winningNumbersDto);

        //when
        LotteryResultDto result = resultCheckerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers().get()).isEqualTo(List.of(1, 2, 8, 9, 10, 11)),
                () -> assertThat(result.winningNumbers().get()).isEqualTo(List.of(1, 2, 3, 4, 5, 6)),
                () -> assertThat(result.hitNumbers().getAsInt()).isEqualTo(2),
                () -> assertThat(result.message()).isEqualTo("you lost!"));
    }

    @Test
    void should_return_result_with_empty_credentials_and_invalid_message_when_user_gave_invalid_id() {
        //given
        WinningNumbersDto winningNumbersDto = new WinningNumbersDto(Optional.of(UUID.randomUUID()), Optional.of(List.of(1, 2, 3, 4, 5, 6)), randomDate);

        given(numberReceiverFacade.retrieveUserNumbers(any(UUID.class))).willReturn(null);
        given(numbersGeneratorFacade.retrieveWonNumbers(randomDate)).willReturn(winningNumbersDto);

        //when
        LotteryResultDto result = resultCheckerFacade.checkWinner(UUID.randomUUID());

        //then
        assertAll(() -> assertThat(result.yourNumbers().isPresent()).isEqualTo(false),
                () -> assertThat(result.winningNumbers().isPresent()).isEqualTo(false),
                () -> assertThat(result.hitNumbers().isPresent()).isEqualTo(false),
                () -> assertThat(result.message()).isEqualTo("invalid id"));
    }
}