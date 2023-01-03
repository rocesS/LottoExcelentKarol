package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.util.List;

record AllUserNumbersByDate(List<LotteryTicketDto> allUserNumbers) {
}