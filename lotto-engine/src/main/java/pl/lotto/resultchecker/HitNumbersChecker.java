package pl.lotto.resultchecker;

import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultchecker.dto.WinningNumbersDto;

import java.util.List;

class HitNumbersChecker {
    private static final int minWonNumbers = 3;
    NumberReceiverFacade numberReceiverFacade;
    RetrieverWonNumbersClient retrieverWonNumbersClient;

    HitNumbersChecker(NumberReceiverFacade numberReceiverFacade, RetrieverWonNumbersClient retrieverWonNumbersClient) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.retrieverWonNumbersClient = retrieverWonNumbersClient;
    }

    LotteryResult checkIfUserWon(String id) {
        LotteryTicketDto lotteryTicketDto = numberReceiverFacade.retrieveUserNumbers(id);
        if (lotteryTicketDto == null) {
            return new LotteryResult(null, null, 0, LotteryMessage.INVALID_ID.message);
        }

        String drawDate = lotteryTicketDto.drawDate();
        WinningNumbersDto winningNumbersDto = retrieverWonNumbersClient.retrieveWonNumbers(drawDate);

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



