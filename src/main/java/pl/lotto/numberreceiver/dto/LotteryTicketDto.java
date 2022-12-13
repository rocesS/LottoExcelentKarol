package pl.lotto.numberreceiver.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class LotteryTicketDto {
    UUID id;
    List<Integer> numbers;
    LocalDateTime drawDate;

    public LotteryTicketDto(UUID id, List<Integer> numbers, LocalDateTime drawDate) {
        this.id = id;
        this.numbers = numbers;
        this.drawDate = drawDate;
    }

    public LocalDateTime getDrawDate() {
        return drawDate;
    }

    public List<Integer> getNumbers() { return numbers; }

}
