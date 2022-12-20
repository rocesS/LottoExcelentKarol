package pl.lotto.numberreceiver;

import pl.lotto.numberreceiver.dto.AllUserNumbersByDateDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

class TicketRepositoryInMemoryImpl implements TicketRepository {

    Map<UUID, LotteryTicket> userNumbers = new HashMap<>();

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
        List<List<Integer>> numbers = userNumbers.values()
                .stream()
                .filter(lotteryTicket -> lotteryTicket.drawDate().compareTo(date) == 0)
                .map(LotteryTicket::numbers)
                .collect(Collectors.toList());
        return new AllUserNumbersByDate(numbers);
    }


}
