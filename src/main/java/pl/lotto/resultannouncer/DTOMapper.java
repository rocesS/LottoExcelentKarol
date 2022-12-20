package pl.lotto.resultannouncer;

import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

class DTOMapper {
    LotteryAnnouncementDto mapLotteryResultsToDto(LotteryAnnouncement lotteryResults) {
        return new LotteryAnnouncementDto(lotteryResults.message(), lotteryResults.yourNumbers(),
                lotteryResults.winningNumbers(), lotteryResults.hitNumbers());
    }
}
