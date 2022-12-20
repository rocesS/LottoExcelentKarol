package pl.lotto.numberreceiver.dto;

import java.util.List;

public record AllUserNumbersByDateDto (List<List<Integer>> allUserNumbers) {}
