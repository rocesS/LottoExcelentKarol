package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

class HitNumbersChecker {
    private static final int minWonNumbers = 3;
    NumberReceiverFacade numberReceiverFacade;
    NumbersGeneratorFacade numbersGeneratorFacade;

    HitNumbersChecker(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
    }

    LotteryResult checkIfUserWon(UUID id) {
        LotteryTicketDto lotteryTicketDto = numberReceiverFacade.retrieveUserNumbers(id);
        if (lotteryTicketDto == null) {
            return new LotteryResult(null, null, 0, LotteryMessage.INVALID_ID.message);
        }

        LocalDateTime drawDate = lotteryTicketDto.drawDate();
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.retrieveWonNumbers(drawDate);

        if (winningNumbersDto.winningNumbers() != null) {
            List<Integer> userNumbers = lotteryTicketDto.numbers();
            List<Integer> winningNumbers = winningNumbersDto.winningNumbers();
            int hitNumbers = checkHowManyNumbersWasHit(userNumbers, winningNumbers);
            if (hitNumbers >= minWonNumbers) {
                return new LotteryResult(userNumbers, winningNumbers, hitNumbers, LotteryMessage.WIN.message);
            } else {
                return new LotteryResult(userNumbers, winningNumbers, hitNumbers, LotteryMessage.LOSE.message);
            }
        }

        return new LotteryResult(null, null, 0, LotteryMessage.NO_DRAW.message);
    }

    private int checkHowManyNumbersWasHit(List<Integer> userNumbers, List<Integer> winningNumbers) {
        return (int) userNumbers.stream().filter(winningNumbers::contains).count();
    }
}



