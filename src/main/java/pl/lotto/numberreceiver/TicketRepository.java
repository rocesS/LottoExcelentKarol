package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
interface TicketRepository extends MongoRepository<LotteryTicket, UUID> {

    void addUserNumbers(UUID id, LotteryTicket lotteryTicket);
    //LotteryTicket getLotteryTicketById(UUID id);
    AllUserNumbersByDate retrieveAllUsersByDate(LocalDateTime date);
}
