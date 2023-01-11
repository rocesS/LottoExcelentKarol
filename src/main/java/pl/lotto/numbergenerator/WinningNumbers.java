package pl.lotto.numbergenerator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "winningNumbers")
class WinningNumbers {
    @Id
    private String id;
    private final List<Integer> winningNumbers;
    private final LocalDateTime drawDate;

    WinningNumbers(List<Integer> winningNumbers, LocalDateTime drawDate) {
        this.winningNumbers = winningNumbers;
        this.drawDate = drawDate;
    }

    List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    LocalDateTime getDrawDate() {
        return drawDate;
    }
}
