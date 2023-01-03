package pl.lotto.numbergenerator;

import pl.lotto.numbergenerator.dto.WinningNumbersDto;

class DtoMapper {
    static WinningNumbersDto mapWinningNumbersToDto(WinningNumbers winningNumbers) {
        return new WinningNumbersDto(winningNumbers.winningNumbers());
    }
}
