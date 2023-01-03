package pl.lotto.numbergenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class NumbersGeneratorFacadeTest {

    WinningNumbersRepository winningNumbersRepositoryTest;
    LocalDateTime drawDate;

    @BeforeEach
    void init() {
        // sample date to get next saturday of it
        LocalDateTime randomDate = LocalDateTime.of(2017, 2, 13, 15, 56);
        drawDate = randomDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);

        winningNumbersRepositoryTest = new WinningNumbersRepositoryTest();
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        winningNumbersRepositoryTest.addWinningNumbers(drawDate, numbers);
    }

    @Test
    void should_return_correct_numbers_when_query_date_is_after_draw_date_and_winning_numbers_are_generated() {
        //given
        LocalDateTime queryDate = drawDate.plusHours(1);
        NumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createFacadeForTest(winningNumbersRepositoryTest, queryDate);

        //when
        WinningNumbersDto result = numbersGeneratorFacade.retrieveWonNumbers(drawDate);
        List<Integer> numbers = result.winningNumbers().get();
        int correctDigits = (int) numbers.stream()
                .filter(a -> a > 0 && a < 100)
                .distinct().count();

        //then
        assertThat(correctDigits).isEqualTo(6);
    }

    @Test
    void should_return_null_when_query_date_is_before_draw_date() {
        //given
        LocalDateTime queryDate = drawDate.minusHours(1);
        NumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createFacadeForTest(winningNumbersRepositoryTest, queryDate);

        //when
        WinningNumbersDto result = numbersGeneratorFacade.retrieveWonNumbers(drawDate);

        //then
        assertThat(result.winningNumbers().isEmpty()).isEqualTo(true);
    }
}