package pl.lotto.numbergenerator;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
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
