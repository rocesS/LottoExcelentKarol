package pl.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//@EnableMongoRepositories(basePackageClasses = {pl.lotto.numberreceiver.TicketRepository.class, pl.lotto.numbergenerator.WinningNumbersRepository.class})
@SpringBootApplication()
public class LottoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
    }
}
