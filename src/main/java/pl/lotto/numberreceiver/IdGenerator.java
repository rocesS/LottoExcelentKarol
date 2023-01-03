package pl.lotto.numberreceiver;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class IdGenerator {
    UUID generateId() {
        return UUID.randomUUID();
    }
}
