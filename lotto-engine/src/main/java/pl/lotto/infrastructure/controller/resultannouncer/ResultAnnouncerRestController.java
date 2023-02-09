package pl.lotto.infrastructure.controller.resultannouncer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.resultannouncer.dto.LotteryAnnouncementDto;

@RestController
public class ResultAnnouncerRestController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;

    ResultAnnouncerRestController(ResultAnnouncerFacade resultAnnouncerFacade) {
        this.resultAnnouncerFacade = resultAnnouncerFacade;
    }

    @GetMapping("/announcement/{id}")
    ResponseEntity<LotteryAnnouncementDto> checkWinner(@PathVariable String id) {
        if (!resultAnnouncerFacade.isValidUUID(id)) {
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




