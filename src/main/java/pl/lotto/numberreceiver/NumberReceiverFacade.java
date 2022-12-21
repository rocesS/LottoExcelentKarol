package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class NumberReceiverFacade {
    DTOMapper dtoMapper;
    NumberValidator numberValidator;
    TicketGenerator ticketGenerator;
    TicketRepository ticketRepository;


    NumberReceiverFacade(NumberValidator numberValidator, TicketGenerator ticketGenerator, TicketRepository ticketRepository, DTOMapper dtoMapper) {
        this.numberValidator = numberValidator;
        this.ticketGenerator = ticketGenerator;
        this.ticketRepository = ticketRepository;
        this.dtoMapper = dtoMapper;
    }

    public Optional<LotteryTicketDto> inputNumbers(Collection<Integer> numbersFromUser) {
        if (numberValidator.validate(numbersFromUser)) {
            LotteryTicket lotteryTicket = ticketGenerator.generateTicket(numbersFromUser);
            ticketRepository.addUserNumbers(lotteryTicket.id(), lotteryTicket);
            return Optional.of(dtoMapper.mapLotteryTicketToDto(lotteryTicket));
        }
        return Optional.ofNullable(null);
    }

    public LotteryTicketDto retrieveUserNumbers(UUID id) {
        LotteryTicket lotteryTicket = ticketRepository.getTicketById(id);
        return dtoMapper.mapLotteryTicketToDto(lotteryTicket);
    }

    public AllUserNumbersByDateDto retrieveUserNumbers(LocalDateTime drawDate) {
        return dtoMapper.mapAllUserNumbersByDateToDto(ticketRepository.retrieveAllUsersByDate(drawDate));
    }

}
