package pl.lotto.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
interface WinningNumbersRepository extends MongoRepository<WinningNumbers, UUID> {
    void addWinningNumbers(LocalDateTime drawDate, List<Integer> numbers);

    WinningNumbers getWinningNumbers(LocalDateTime drawDate);
}
