package pl.lotto.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, String> {
    WinningNumbers findByDrawDate(String drawDate);
}
