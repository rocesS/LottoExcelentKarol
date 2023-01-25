package pl.lotto.numberreceiver;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "lotteryTickets")
public record LotteryTicket (@Id String id, List<Integer> numbers, String drawDate, String message){
}

