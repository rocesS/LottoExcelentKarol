package pl.lotto.numbergenerator;

import java.util.*;

public class NumbersGenerator {
    List<Integer> generateNumbers() {
        final int amountOfNumbers = 6;
        Set<Integer> generatedNumbers = new HashSet<>();
        Random rand = new Random();
        while(generatedNumbers.size() < amountOfNumbers) {
            generatedNumbers.add(rand.nextInt(99) + 1);
        }
        return new ArrayList<>(generatedNumbers);
    }
}
