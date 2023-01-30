package pl.lotto.infrastructure.controller.resultannouncer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.AnnouncerRequestDto;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

import java.util.List;

@RestController
public class ResultAnnouncerRestController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;

    ResultAnnouncerRestController(ResultAnnouncerFacade resultAnnouncerFacade) {
        this.resultAnnouncerFacade = resultAnnouncerFacade;
    }

    @GetMapping("/announcement/{id}")
    ResponseEntity<LotteryAnnouncementDto> checkWinner(@PathVariable String id) {
        boolean tr = resultAnnouncerFacade.isValidUUID(id);
        boolean tre = resultAnnouncerFacade.isValidUUID(id);
        if (!tr) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new LotteryAnnouncementDto(null, null, 0, "invalid id"));
        }

        LotteryAnnouncementDto announcement = resultAnnouncerFacade.checkWinner(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(announcement);
    }
}




