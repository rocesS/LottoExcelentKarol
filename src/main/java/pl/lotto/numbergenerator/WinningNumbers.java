package pl.lotto.numbergenerator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "winningNumbers")
class WinningNumbers {
    @Id
    private UUID id;
    private final List<Integer> winningNumbers;
    private final String drawDate;

    public WinningNumbers(UUID id, List<Integer> winningNumbers, String drawDate) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.drawDate = drawDate;
    }

    UUID getId() {
        return id;
    }

    List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    String getDrawDate() {
        return drawDate;
    }
}
