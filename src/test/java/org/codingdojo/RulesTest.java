package org.codingdojo;

import org.codingdojo.game.yatzy.YatzyRules;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RulesTest {

    private final YatzyRules rulesToTest = new YatzyRules();
    @Test
    void test_contiguousInArray() {
        ArrayList<Integer> listTest1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest1);
        assertEquals(10, this.rulesToTest.countContiguousElement(listTest1.stream().mapToInt(i -> i).toArray()));

        ArrayList<Integer> listTest2 = Stream.of(1, 2, 3, 4, 4, 6, 7, 7, 9, 10).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest2);
        assertEquals(4, this.rulesToTest.countContiguousElement(listTest2.stream().mapToInt(i -> i).toArray()));

        ArrayList<Integer> listTest3 = Stream.of(1, 2, 3, 4, 4, 5, 6, 7, 7, 10).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest3);
        assertEquals(7, this.rulesToTest.countContiguousElement(listTest3.stream().mapToInt(i -> i).toArray()));

        ArrayList<Integer> listTest4 = Stream.of(1, 2, 2, 3, 3, 3, 4, 4, 4, 4).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest4);
        assertEquals(4, this.rulesToTest.countContiguousElement(listTest4.stream().mapToInt(i -> i).toArray()));

        ArrayList<Integer> listTest5 = Stream.of(1, 2, 3, 10, 11, 12, 13, 21, 22, 23).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest5);
        assertEquals(4, this.rulesToTest.countContiguousElement(listTest5.stream().mapToInt(i -> i).toArray()));

        ArrayList<Integer> listTest6 = Stream.of(1, 2, 3, 3, 3, 5, 6, 7, 8, 9).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest6);
        assertEquals(5, this.rulesToTest.countContiguousElement(listTest6.stream().mapToInt(i -> i).toArray()));
    }
}
