package pl.lotto.numbergenerator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "winningNumbers")
public class WinningNumbers {
    @Id
    private String id;
    private final List<Integer> winningNumbers;
    private final String drawDate;

    public WinningNumbers(String id, List<Integer> winningNumbers, String drawDate) {
        this.id = id;
        this.winningNumbers = winningNumbers;
        this.drawDate = drawDate;
    }

    String getId() {
        return id;
    }

    List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    String getDrawDate() {
        return drawDate;
    }
}
