package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultchecker.dto.ResultCheckerDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ResultCheckerFacade {
    NumberReceiverFacade numberReceiverFacade;
    NumbersGeneratorFacade numbersGeneratorFacade;

    public ResultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumbersGeneratorFacade numbersGeneratorFacade) {
        this.numberReceiverFacade = numberReceiverFacade;
        this.numbersGeneratorFacade = numbersGeneratorFacade;
    }

    public ResultCheckerDto checkWinner(UUID id) {
        LotteryTicketDto lotteryTicketDto = numberReceiverFacade.retrieveUserNumbers(id);
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.retrieveWonNumbers(lotteryTicketDto.getDrawDate());
        if (winningNumbersDto.isDrawCompleted()) {
            if (hasAtLeastThreeNumberTheSame(lotteryTicketDto.getNumbers(), winningNumbersDto.getWinningNumbers())) {
                return new ResultCheckerDto("user won!");
            }
            else {
                return new ResultCheckerDto("draw uncompleted!");
            }
        }
        else {
            return new ResultCheckerDto("user didn't win");
        }
    }

    private boolean hasAtLeastThreeNumberTheSame(List<Integer> userNumbers, List<Integer> winningNumbers) {
        return userNumbers.stream().filter(winningNumbers::contains).count() >= 3;
    }
}
