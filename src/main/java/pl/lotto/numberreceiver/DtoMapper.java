package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.util.Optional;

class DtoMapper {

    static LotteryTicketDto mapLotteryTicketToDto(LotteryTicket lotteryTicket) {
        return new LotteryTicketDto(Optional.ofNullable(lotteryTicket.id()), lotteryTicket.numbers(),
                Optional.ofNullable(lotteryTicket.drawDate()), lotteryTicket.message());
    }

    static AllUserNumbersByDateDto mapAllUserNumbersByDateToDto(AllUserNumbersByDate allUserNumbersByDate) {
        return new AllUserNumbersByDateDto(allUserNumbersByDate.allUserNumbers());
    }
}
