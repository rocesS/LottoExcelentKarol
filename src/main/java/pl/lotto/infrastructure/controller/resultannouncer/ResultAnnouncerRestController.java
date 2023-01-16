package pl.lotto.infrastructure.controller.resultannouncer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;
import pl.lotto.resultannouncer.dto.RequestDto;

import java.util.UUID;

@RestController
public class ResultAnnouncerRestController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;

    ResultAnnouncerRestController(ResultAnnouncerFacade resultAnnouncerFacade) {
        this.resultAnnouncerFacade = resultAnnouncerFacade;
    }

    @PostMapping("/announcement")
    ResponseEntity<LotteryAnnouncementDto> checkWinner(@RequestBody RequestDto request) {
        UUID id = request.id();
        LotteryAnnouncementDto announcement = resultAnnouncerFacade.checkWinner(id);
        if (announcement.message().equals("you won!")) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(announcement);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(announcement);
        }

    }

}
