package pl.lotto.resultchecker;

import pl.lotto.resultchecker.dto.LotteryResultDto;

class DtoMapper {
    static LotteryResultDto mapLotteryResultToDto(LotteryResult lotteryResult) {
        return new LotteryResultDto(lotteryResult.yourNumbers(), lotteryResult.winningNumbers(), lotteryResult.hitNumbers(), lotteryResult.message());
    }
}
