package pl.lotto.numbergenerator;

import java.util.*;

class NumbersGenerator {
    List<Integer> generateNumbers() {
        Set<Integer> generatedNumbers = new HashSet<>();
        Random rand = new Random();
        final int numOfNumbers = 6;
        final int bound = 99;
        while(generatedNumbers.size() < numOfNumbers) {
            generatedNumbers.add(rand.nextInt(bound) + 1);
        }
        return new ArrayList<>(generatedNumbers);
    }
}
