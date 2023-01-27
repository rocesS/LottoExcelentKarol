package pl.lotto.infrastructure.controller.resultannouncer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.AnnouncerRequestDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

@RestController
public class ResultAnnouncerRestController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;

    ResultAnnouncerRestController(ResultAnnouncerFacade resultAnnouncerFacade) {
        this.resultAnnouncerFacade = resultAnnouncerFacade;
    }

    @PostMapping("/announcement")
    ResponseEntity<LotteryAnnouncementDto> checkWinner(@Valid @RequestBody AnnouncerRequestDto request) {
        String id = request.getId();
        LotteryAnnouncementDto announcement = resultAnnouncerFacade.checkWinner(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(announcement);
    }
}




