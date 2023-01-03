package pl.lotto.resultchecker;

import org.springframework.stereotype.Service;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.numberreceiver.NumberReceiverFacade;
import pl.lotto.numberreceiver.dto.LotteryTicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Service
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
            return new LotteryResult(Optional.empty(), Optional.empty(), OptionalInt.empty(), "invalid id");
        }

        Optional<LocalDateTime> drawDate = lotteryTicketDto.drawDate();
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.retrieveWonNumbers(drawDate.get());

        if (winningNumbersDto.winningNumbers().isPresent()) {
            List<Integer> userNumbers = lotteryTicketDto.numbers();
            List<Integer> winningNumbers = winningNumbersDto.winningNumbers().get();
            int hitNumbers = checkHowManyNumbersWasHit(userNumbers, winningNumbers);
            if (hitNumbers >= minWonNumbers) {
                return new LotteryResult(Optional.of(userNumbers), Optional.of(winningNumbers), OptionalInt.of(hitNumbers), "you won!");
            } else {
                return new LotteryResult(Optional.of(userNumbers), Optional.of(winningNumbers), OptionalInt.of(hitNumbers), "you lost!");
            }
        }


        return null;
    }

    private int checkHowManyNumbersWasHit(List<Integer> userNumbers, List<Integer> winningNumbers) {
        return (int) userNumbers.stream().filter(winningNumbers::contains).count();
    }



    /*
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
     */
}



