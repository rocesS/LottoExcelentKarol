package pl.lotto.numberreceiver;

import java.util.List;

public class NumberValidator {

    public static final int MIN = 1;
    public static final int MAX = 99;
    public static final int AMOUNT_OF_NUMBERS = 6;

    List<Integer> numbers;

    boolean validate(List<Integer> numbers) {
        this.numbers = numbers;
        return listContainsSixNumbers() && numbersInValidRange() && listContainsDistinctNumbers();
    }

    private boolean listContainsSixNumbers() {
        return numbers.size() == AMOUNT_OF_NUMBERS;
    }

    private boolean listContainsDistinctNumbers() {
        return numbers.stream().distinct().count() == AMOUNT_OF_NUMBERS;
    }

    private boolean numbersInValidRange() {
        return numbers.stream().allMatch(a -> a >= MIN && a <= MAX);
    }


}
