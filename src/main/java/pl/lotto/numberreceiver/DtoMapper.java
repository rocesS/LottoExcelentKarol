package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

class DtoMapper {

    static LotteryTicketDto mapLotteryTicketToDto(LotteryTicket lotteryTicket) {
        return new LotteryTicketDto(lotteryTicket.id(), lotteryTicket.numbers(), lotteryTicket.drawDate(), lotteryTicket.message());
    }

    static AllUserNumbersByDateDto mapAllUserNumbersByDateToDto(AllUserNumbersByDate allUserNumbersByDate) {
        return new AllUserNumbersByDateDto(allUserNumbersByDate.allUserNumbers());
    }
}
