package pl.lotto.numberreceiver;

import java.util.Collection;

class NumberValidator {
    private static final int MIN = 1;
    private static final int MAX = 99;
    private static final int NUM_OF_NUMBERS = 6;

    boolean validate(Collection<Integer> numbers) {
        return CollectionContainsSixNumbers(numbers)
                && numbersInValidRange(numbers)
                && CollectionContainsDistinctNumbers(numbers);
    }

    private boolean CollectionContainsSixNumbers(Collection<Integer> numbers) {
        return numbers.size() == NUM_OF_NUMBERS;
    }

    private boolean CollectionContainsDistinctNumbers(Collection<Integer> numbers) {
        int actualAmountOfNumbers = (int) numbers.stream()
                .distinct()
                .count();
        return actualAmountOfNumbers == NUM_OF_NUMBERS;
    }

    private boolean numbersInValidRange(Collection<Integer> numbers) {
        return numbers.stream()
                .allMatch(a -> a >= MIN && a <= MAX);
    }
}
