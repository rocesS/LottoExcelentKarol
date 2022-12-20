package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.util.ArrayList;
import java.util.List;



class DTOMapper {

    LotteryTicketDto mapLotteryTicketToDto(LotteryTicket lotteryTicket) {
        return new LotteryTicketDto(lotteryTicket.id(), lotteryTicket.numbers(), lotteryTicket.drawDate());
    }

    AllUserNumbersByDateDto mapAllUserNumbersByDateToDto(AllUserNumbersByDate allUserNumbersByDate) {
        return new AllUserNumbersByDateDto(allUserNumbersByDate.allUserNumbers());
    }

    /*
    List<LotteryTicketDto> lotteryTicketListMapper(List<LotteryTicket> lotteryTicketList) {
        return lotteryTicketList.stream()
                .collect(
                        ArrayList::new,
                        (list, ticket) -> list.add(new LotteryTicketDto(ticket.getId(), ticket.getNumbers(), ticket.getDrawDate())),
                        ArrayList::addAll
                );
    }
     */

    /*
    InputNumbersDto inputNumbersMapper(InputNumbers inputNumbers) {
        LotteryTicketDto lotteryTicketDto = lotteryTicketMapper(inputNumbers.getLotteryTicket());
        return new InputNumbersDto(lotteryTicketDto, inputNumbers.getValidationMessage());
    }

     */
}
