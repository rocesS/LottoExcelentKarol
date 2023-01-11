package pl.lotto.numbergenerator;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, UUID> {
    WinningNumbers findByDrawDate(LocalDateTime drawDate);
}
