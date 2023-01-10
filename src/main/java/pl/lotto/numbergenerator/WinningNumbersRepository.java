package pl.lotto.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, UUID> {
    @Query("{drawDate : ?0}")
    WinningNumbers findByDrawDate(LocalDateTime drawDate);
}
