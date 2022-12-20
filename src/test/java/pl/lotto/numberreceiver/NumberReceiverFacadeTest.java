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

public class NumberReceiverFacadeTest {

    TicketRepository ticketRepository;
    @BeforeEach
    void init() {
        ticketRepository = new TicketRepositoryInMemoryImpl();
    }

    @ParameterizedTest
    @MethodSource("createListsWithNumbersOutOfRange")
    public void should_return_ticket_with_lottery_id_and_draw_date_when_user_gave_six_numbers_out_of_range(List<Integer> numbersFromUser){
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, LocalDateTime.now());

        //when
        Optional<LotteryTicketDto> result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        assertThat(result.orElse(null)).isNull();
    }

    private static Stream<Arguments> createListsWithNumbersOutOfRange() {
        return Stream.of(
                Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 100))
        );
    }

    /*
    @Test
    public void second_should_return_ticket_with_lottery_id_and_draw_date_when_user_gave_six_numbers_in_range_of_1_to_99(){
        //given
        TicketGenerator ticketGenerator = mock(TicketGenerator.class);
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        UUID id = UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002");
        LocalDateTime date = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
        given(ticketGenerator.generateTicket(anyCollection())).willReturn(new LotteryTicket(id, numbersFromUser, date));
        //when
        Optional<InputNumbersDto> result = numberReceiverFacade.inputNumbers(numbersFromUser);
        //then
    }
    */



    @ParameterizedTest
    @MethodSource("createListsWithWrongAmountOfNumbers")
    public void should_return_invalid_ticket_when_user_gave_not_six_numbers(List<Integer> numbersFromUser){
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, LocalDateTime.now());

        // when
        Optional<LotteryTicketDto> result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        assertThat(result.orElse(null)).isNull();
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
    public void should_return_result_without_lottery_id_and_without_draw_date_when_user_gave_at_least_one_duplicate(){
        // given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, LocalDateTime.now());
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 4, 5);

        // when
        Optional<LotteryTicketDto> result = numberReceiverFacade.inputNumbers(numbersFromUser);

        // then
        assertThat(result.orElse(null)).isNull();
    }

    // why to use clock?
    @Test
    public void should_return_saturday_as_next_draw_date() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createFacadeForTest(ticketRepository, LocalDateTime.now());
        List<Integer> numbersFromUser = List.of(1, 2, 3, 4, 5, 6);
        LocalDateTime nextDrawDate = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .withHour(12).withMinute(0).withSecond(0).withNano(0);

        // when
        Optional<LotteryTicketDto> result = numberReceiverFacade.inputNumbers(numbersFromUser);

        //then
        result.ifPresent(lotteryTicketDto -> assertThat(lotteryTicketDto.drawDate()).isEqualTo(nextDrawDate));
    }

    @Test
    public void should_return_all_numbers_when_draw_date_was_given() {
        //given
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime drawDate = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .withHour(12).withMinute(0).withSecond(0).withNano(0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, date);
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
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime drawDate = date.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .withHour(12).withMinute(0).withSecond(0).withNano(0);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration().createFacadeForTest(ticketRepository, date);
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
            numberReceiverFacade.inputNumbers(List.of(1, 2, 3, 4, 5, 6))
                    .ifPresent(lotteryTicketDto -> {UUID id = lotteryTicketDto.id(); allUsersId.add(id);});
            numberReceiverFacade.inputNumbers(List.of(1, 3, 5, 7, 9, 11))
                    .ifPresent(lotteryTicketDto -> {UUID id = lotteryTicketDto.id(); allUsersId.add(id);});
            numberReceiverFacade.inputNumbers(List.of(22, 33, 1, 2, 3, 4))
                    .ifPresent(lotteryTicketDto -> {UUID id = lotteryTicketDto.id(); allUsersId.add(id);});
        }

        //then
        assertThat(allUsersId.size()).isEqualTo(repetitions * generatedIdByOneRepetition);
    }
}
