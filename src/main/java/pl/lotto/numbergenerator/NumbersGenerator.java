package pl.lotto.numbergenerator;

import java.util.*;

class NumbersGenerator {

    private static final int numOfNumbers = 6;
    private static final int bound = 99;

    List<Integer> generateNumbers() {
        Set<Integer> generatedNumbers = new HashSet<>();
        Random rand = new Random();

        while (generatedNumbers.size() < numOfNumbers) {
            generatedNumbers.add(rand.nextInt(bound) + 1);
        }
        return new ArrayList<>(generatedNumbers);
    }
}
