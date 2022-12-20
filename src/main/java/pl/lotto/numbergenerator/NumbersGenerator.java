package pl.lotto.numbergenerator;

import java.util.*;

class NumbersGenerator {
    List<Integer> generateNumbers() {
        Set<Integer> generatedNumbers = new HashSet<>();
        Random rand = new Random();
        int amountOfNumbers = 6;
        while(generatedNumbers.size() < amountOfNumbers) {
            generatedNumbers.add(rand.nextInt(99) + 1);
        }
        return new ArrayList<>(generatedNumbers);
    }
}
