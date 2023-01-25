package pl.lotto.resultchecker;

import org.springframework.stereotype.Service;
import pl.lotto.resultchecker.dto.WinningNumbersDto;

@Service
public interface RetrieverWonNumbersClient {
    WinningNumbersDto retrieveWonNumbers(String drawDate);
}
