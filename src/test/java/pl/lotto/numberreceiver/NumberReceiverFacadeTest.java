package pl.lotto.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;



public class NumberReceiverFacadeTest {

    TicketRepository ticketRepository;
    LocalDateTime dateToGenerateDrawDate;
    LocalDateTime drawDate;
    @BeforeEach
    void init() {
        ticketRepository = new TicketRepositoryInMemoryImpl();
        dateToGenerateDrawDate = LocalDateTime.of(2017, 2, 13, 15, 56);
        drawDate = dateToGenerateDrawDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
    }

    @ParameterizedTest
    @MethodSource("createListsWithNumbersOutOfRange")
    public void should_return_invalid_ticket_when_user_gave_six_numbers_out_of_range(List<Integer> numbersFromUser){
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, dateToGenerateDrawDate);

        //when
        LotteryTicketDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        assertAll(() -> assertThat(result.id().isEmpty()).isEqualTo(true),
                () -> assertThat(result.numbers()).isEqualTo(numbersFromUser),
                () -> assertThat(result.drawDate().isEmpty()).isEqualTo(true),
                () -> assertThat(result.message()).isEqualTo("invalid"));
    }

    private static Stream<Arguments> createListsWithNumbersOutOfRange() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 100))
        );
    }

    @Test
    public void should_return_ticket_with_correct_credentials_when_user_gave_six_numbers_in_range_of_1_to_99(){
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, dateToGenerateDrawDate);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);

        //when
        LotteryTicketDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        //then
        assertAll(() -> assertThat(result.id().isPresent()).isEqualTo(true),
                () -> assertThat(result.numbers()).isEqualTo(numbersFromUser),
                () -> assertThat(result.drawDate().get()).isEqualTo(drawDate),
                () -> assertThat(result.message()).isEqualTo("valid"));
    }




    @ParameterizedTest
    @MethodSource("createListsWithWrongAmountOfNumbers")
    public void should_return_invalid_ticket_when_user_gave_not_six_numbers(List<Integer> numbersFromUser){
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, LocalDateTime.now());

        // when
        LotteryTicketDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        assertAll(() -> assertThat(result.id().isEmpty()).isEqualTo(true),
                () -> assertThat(result.numbers()).isEqualTo(numbersFromUser),
                () -> assertThat(result.drawDate().isEmpty()).isEqualTo(true),
                () -> assertThat(result.message()).isEqualTo("invalid"));
    }

    private static Stream<Arguments> createListsWithWrongAmountOfNumbers() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(List.of(1))
        );
    }


    @Test
    public void should_return_invalid_ticket_when_user_gave_at_least_one_duplicate(){
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, LocalDateTime.now());
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 4, 5);

        // when
        LotteryTicketDto result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        assertAll(() -> assertThat(result.id().isEmpty()).isEqualTo(true),
                () -> assertThat(result.numbers()).isEqualTo(numbersFromUser),
                () -> assertThat(result.drawDate().isEmpty()).isEqualTo(true),
                () -> assertThat(result.message()).isEqualTo("invalid"));
    }

    @Test
    public void should_return_all_numbers_when_draw_date_was_given() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, dateToGenerateDrawDate);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> numbersFromUser2 = List.of(5, 2, 3, 4, 1, 34);
        List<Integer> numbersFromUser3 = List.of(1, 2, 3, 15, 8, 12);

        //when
        numberReceiverFacade.inputNumbers(numbersFromUser);
        numberReceiverFacade.inputNumbers(numbersFromUser2);
        numberReceiverFacade.inputNumbers(numbersFromUser3);
        AllUserNumbersByDateDto result = numberReceiverFacade.retrieveUserNumbers(drawDate);

        //Then
        assertThat(result.allUserNumbers().size()).isEqualTo(3);
    }


    @Test
    public void should_return_zero_numbers_when_all_added_numbers_were_invalid() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, dateToGenerateDrawDate);
        List<Integer> numbersFromUser = List.of(0, 2, 3, 4, 5, 6);
        List<Integer> numbersFromUser2 = List.of(5, 2, 3, 4, 1, 100);
        List<Integer> numbersFromUser3 = List.of(1, 2, 15, 8, 12);

        //when
        numberReceiverFacade.inputNumbers(numbersFromUser);
        numberReceiverFacade.inputNumbers(numbersFromUser2);
        numberReceiverFacade.inputNumbers(numbersFromUser3);
        AllUserNumbersByDateDto result = numberReceiverFacade.retrieveUserNumbers(drawDate);

        //then
        assertThat(result.allUserNumbers().size()).isEqualTo(0);
    }

    @Test
    void should_generate_unique_id() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, LocalDateTime.now());
        Set<UUID> allUsersId = new HashSet<>();
        int repetitions = 50;
        int generatedIdByOneRepetition = 3;

        //when
        for(int i = 0; i < repetitions; i++) {
            UUID id = numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6)).id().get();
            allUsersId.add(id);
            id = numberReceiverFacade.inputNumbers(List.of(1, 3, 5, 7, 9, 11)).id().get();
            allUsersId.add(id);
            id = numberReceiverFacade.inputNumbers(List.of(22, 33, 1, 2, 3, 4)).id().get();
            allUsersId.add(id);
        }

        //then
        assertThat(allUsersId.size()).isEqualTo(repetitions * generatedIdByOneRepetition);
    }
}
