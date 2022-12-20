package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

import java.util.Optional;

class DTOMapper {
    WinningNumbersDto mapWinningNumbersToDto(WinningNumbers winningNumbers) {
        return new WinningNumbersDto(winningNumbers.winningNumbers());
    }
}
