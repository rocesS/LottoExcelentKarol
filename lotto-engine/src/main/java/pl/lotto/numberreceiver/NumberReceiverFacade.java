package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.util.Collection;
import java.util.Optional;

public class NumberReceiverFacade {
    NumberValidator numberValidator;
    TicketGenerator ticketGenerator;
    TicketRepository ticketRepository;

    NumberReceiverFacade(NumberValidator numberValidator, TicketGenerator ticketGenerator, TicketRepository ticketRepository) {
        this.numberValidator = numberValidator;
        this.ticketGenerator = ticketGenerator;
        this.ticketRepository = ticketRepository;
    }

    public LotteryTicketDto inputNumbers(Collection<Integer> numbersFromUser) {
        if (numberValidator.validate(numbersFromUser)) {
            LotteryTicket lotteryTicket = ticketGenerator.generateTicket(numbersFromUser);
            if (lotteryTicket.id() != null) {
                ticketRepository.insert(lotteryTicket);
            }
            return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
        }
        LotteryTicket lotteryTicket = ticketGenerator.generateInvalidTicket(numbersFromUser);
        return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
    }

    public LotteryTicketDto retrieveUserNumbers(String id) {
        Optional<LotteryTicket> lotteryTicket = ticketRepository.findById(id);
        return lotteryTicket.map(DtoMapper::mapLotteryTicketToDto).orElse(null);
    }

}
