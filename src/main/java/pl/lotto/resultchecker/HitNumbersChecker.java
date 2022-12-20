package pl.lotto.resultchecker;

import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;
import pl.lotto.resultchecker.dto.LotteryResultDto;

import java.util.List;
import java.util.Optional;

class HitNumbersChecker {
    NumbersGeneratorFacade numbersGeneratorFacade;

    HitNumbersChecker(NumbersGeneratorFacade numbersGeneratorFacade) {
        this.numbersGeneratorFacade = numbersGeneratorFacade;
    }

    LotteryResult checkIfUserWon(LotteryTicketDto lotteryTicketDto) {
        Optional<WinningNumbersDto> winningNumbersDto = numbersGeneratorFacade.retrieveWonNumbers(lotteryTicketDto.drawDate());
        if (winningNumbersDto.isPresent()) {
            int hitNumbers = checkHowManyNumbersWasHit(lotteryTicketDto.numbers(), winningNumbersDto.get().winningNumbers());
            final int minWonNumbers = 3;
            if (hitNumbers >= minWonNumbers) {
                return new LotteryResult("you won!", lotteryTicketDto.numbers(), winningNumbersDto.get().winningNumbers(), hitNumbers);
            } else {
                return new LotteryResult("you lost!", lotteryTicketDto.numbers(), winningNumbersDto.get().winningNumbers(), hitNumbers);
            }

        }
        return null;
    }

    private int checkHowManyNumbersWasHit(List<Integer> userNumbers, List<Integer> winningNumbers) {
        final int minSameNumbers = 3;
        return (int) userNumbers.stream().filter(winningNumbers::contains).count();
    }
}



