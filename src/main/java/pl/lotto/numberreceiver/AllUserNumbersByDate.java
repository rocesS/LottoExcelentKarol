package pl.lotto.numberreceiver;
import java.util.List;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

record AllUserNumbersByDate(List<LotteryTicketDto> allUserNumbers) {
}