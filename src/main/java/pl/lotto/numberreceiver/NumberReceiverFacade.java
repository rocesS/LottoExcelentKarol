package pl.lotto.numberreceiver;

import org.springframework.stereotype.Service;
import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
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
            if (lotteryTicket.id().isPresent()) {
                ticketRepository.addUserNumbers(lotteryTicket.id().get(), lotteryTicket);
            }
            return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
        }
        LotteryTicket lotteryTicket = ticketGenerator.generateInvalidTicket(numbersFromUser);
        return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
    }

    public LotteryTicketDto retrieveUserNumbers(UUID id) {
        LotteryTicket lotteryTicket = ticketRepository.getTicketById(id);
        if (lotteryTicket != null) {
            return DtoMapper.mapLotteryTicketToDto(lotteryTicket);
        }
        return null;
    }

    public AllUserNumbersByDateDto retrieveUserNumbers(LocalDateTime drawDate) {
        return DtoMapper.mapAllUserNumbersByDateToDto(ticketRepository.retrieveAllUsersByDate(drawDate));
    }

}
