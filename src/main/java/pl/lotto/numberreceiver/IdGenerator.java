package pl.lotto.numberreceiver;

import org.springframework.stereotype.Service;

import java.util.UUID;

class IdGenerator {
    UUID generateId() {
        return UUID.randomUUID();
    }
}
