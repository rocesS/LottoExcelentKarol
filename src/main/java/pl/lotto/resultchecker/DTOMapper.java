package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultchecker.dto.LotteryResultDto;

class DTOMapper {
    LotteryResultDto mapLotteryResultToDto(LotteryResult lotteryResult) {
        return new LotteryResultDto(lotteryResult.message(), lotteryResult.yourNumbers(), lotteryResult.winningNumbers(), lotteryResult.hitNumbers());
    }
}
