package org.codingdojo.game.core.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface DiceRules extends Rules {

    //WRAPPER METHOD
    /**
     * public only for testing purpose
     * return the highest count of contiguous elements
     */
    default int countContiguousElement(int[] values) {
        int count = 0;
        int maxCount = 0;
        List<Integer> valuesAsList = IntStream.of(values).boxed().sorted().collect(Collectors.toCollection(ArrayList::new));
        int lastValue = -50;
        boolean firstContioguous = false;
        for (int die : valuesAsList) {
            if (die == lastValue + 1) {
                if (!firstContioguous) {
                    firstContioguous = true;
                }
                lastValue = die;
                count++;
            } else if (lastValue > 0 && lastValue + 1 < die) {
                if (count > maxCount) {
                    maxCount = count;
                }
                count = 0;
                firstContioguous = false;
            }
            if (lastValue <= 0 || !firstContioguous) {
                lastValue = die;
            }
        }
        return Math.max(count, maxCount) + 1;
    }
}



