package pl.lotto.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, UUID> {
    WinningNumbers findByDrawDate(String drawDate);
}
