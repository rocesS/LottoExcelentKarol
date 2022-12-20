package pl.lotto.resultannouncer;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.UUID;

public class LotteryTicketDtoObjectTest {
    UUID id = UUID.fromString("5fc155ba-078d-11ed-861d-0242ac120002");
    LocalDateTime date = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
    private LotteryTicketDto lotteryTicketDto = new LotteryTicketDto(id, List.of(1, 2, 3, 4, 5, 6), date);

    public LotteryTicketDto getLotteryTicketDto() {
        return lotteryTicketDto;
    }
}
