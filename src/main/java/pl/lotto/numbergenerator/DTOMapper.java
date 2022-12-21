package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

class DTOMapper {
    WinningNumbersDto mapWinningNumbersToDto(WinningNumbers winningNumbers) {
        return new WinningNumbersDto(winningNumbers.winningNumbers());
    }
}
