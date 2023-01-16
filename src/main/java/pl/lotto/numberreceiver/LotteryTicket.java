package pl.lotto.numberreceiver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(collection = "lotteryTickets")
record LotteryTicket (@Id UUID id, List<Integer> numbers, LocalDateTime drawDate, String message){
}

