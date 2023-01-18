package pl.lotto.numberreceiver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "lotteryTickets")
public record LotteryTicket (@Id UUID id, List<Integer> numbers, String drawDate, String message){
}

