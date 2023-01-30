package pl.lotto.numberreceiver;

import java.util.UUID;

class IdGenerator {
    String generateId() {
        return UUID.randomUUID().toString();
    }
}
