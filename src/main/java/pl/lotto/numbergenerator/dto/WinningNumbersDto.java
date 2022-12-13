package pl.lotto.numbergenerator.dto;

import java.time.LocalDateTime;
import java.util.List;

public class WinningNumbersDto {
    List<Integer> winningNumbers;
    boolean drawCompleted;
    public WinningNumbersDto(boolean drawCompleted, List<Integer> winningNumbers) {
        this.drawCompleted = drawCompleted;
        this.winningNumbers = winningNumbers;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public boolean isDrawCompleted() {
        return drawCompleted;
    }


}
