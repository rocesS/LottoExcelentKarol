package pl.lotto.numberreceiver;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

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
                ticketRepository.addUserNumbers(lotteryTicket.id(), lotteryTicket);
            }
            return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
        }
        LotteryTicket lotteryTicket = ticketGenerator.generateInvalidTicket(numbersFromUser);
        return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
    }

    public LotteryTicketDto retrieveUserNumbers(UUID id) {
        Optional<LotteryTicket> lotteryTicket = ticketRepository.findById(id);
        return lotteryTicket.map(DtoMapper::mapLotteryTicketToDto).orElse(null);
    }

    public AllUserNumbersByDateDto retrieveUserNumbers(LocalDateTime drawDate) {
        return DtoMapper.mapAllUserNumbersByDateToDto(ticketRepository.retrieveAllUsersByDate(drawDate));
    }

}
