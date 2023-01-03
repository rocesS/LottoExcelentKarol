package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


class TicketRepositoryInMemoryImpl implements TicketRepository {

    private final Map<UUID, LotteryTicket> userNumbers = new HashMap<>();

    @Override
    public void addUserNumbers(UUID id, LotteryTicket lotteryTicket) {
        userNumbers.put(id, lotteryTicket);
    }

    @Override
    public LotteryTicket getTicketById(UUID id) {
        return userNumbers.get(id);
    }


    @Override
    public AllUserNumbersByDate retrieveAllUsersByDate(LocalDateTime date) {
        List<LotteryTicketDto> numbers = userNumbers.values()
                .stream()
                .filter(lotteryTicket -> lotteryTicket.drawDate().get().compareTo(date) == 0)
                .map(DtoMapper::mapLotteryTicketToDto)
                .collect(Collectors.toList());
        return new AllUserNumbersByDate(numbers);
    }


}
