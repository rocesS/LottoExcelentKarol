package pl.lotto.infrastructure.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.numbergenerator.NumbersGeneratorFacade;
import pl.lotto.numbergenerator.dto.GeneratorRequestDto;
import pl.lotto.numbergenerator.dto.WinningNumbersDto;

@RestController
public class NumberGeneratorRestController {
    private final NumbersGeneratorFacade numbersGeneratorFacade;


    NumberGeneratorRestController(NumbersGeneratorFacade numbersGeneratorFacade) {
        this.numbersGeneratorFacade = numbersGeneratorFacade;
    }

    @GetMapping("/winningNumbers")
    ResponseEntity<WinningNumbersDto> checkWinner(@RequestParam GeneratorRequestDto request) {
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.retrieveWonNumbers(request.date());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(winningNumbersDto);
    }
}
