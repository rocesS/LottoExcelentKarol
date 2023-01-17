package pl.lotto.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public
interface TicketRepository extends MongoRepository<LotteryTicket, UUID> {
}
