package org.codingdojo;

import org.codingdojo.game.DiceRoll;
import org.codingdojo.game.parameters.GameParameters;
import org.codingdojo.game.yatzy.YatzyCombination;
import org.codingdojo.game.yatzy.YatzyRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DiceRollTest {

    private final YatzyRules rulesToTest = new YatzyRules();

    @Test
    void test_Instantiate_YatzyRoll() {
        ArrayList<Integer> listTest1 = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(listTest1);
        DiceRoll diceRoll = new DiceRoll(this.rulesToTest, listTest1.stream().mapToInt(i -> i).toArray());
        assertEquals(5, diceRoll.score(YatzyCombination.FIVES));
        assertEquals(5, diceRoll.getMaxValue());
    }

    @Test
    void should_throw_IllegalArgumentException() {
        ArrayList<Integer> listTest1 = Stream.of(GameParameters.MIN_DIE_VALUE - 1,
            GameParameters.MIN_DIE_VALUE,
            GameParameters.MIN_DIE_VALUE + 1,
            GameParameters.MAX_DIE_VALUE,
            GameParameters.MAX_DIE_VALUE + 1).collect(Collectors.toCollection(ArrayList::new));
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () ->
            new DiceRoll(this.rulesToTest, listTest1.stream().mapToInt(i -> i).toArray()), "IllegalArgumentException was expected");
        assertEquals("the following value are not between "
                + GameParameters.MIN_DIE_VALUE
                + " and "
                + GameParameters.MAX_DIE_VALUE
                + " : "
                + (GameParameters.MIN_DIE_VALUE - 1)
                + "," + (GameParameters.MAX_DIE_VALUE + 1)
            , thrown1.getMessage());

        ArrayList<Integer> listTest2 = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toCollection(ArrayList::new));
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () ->
                new DiceRoll(this.rulesToTest, listTest2.stream().mapToInt(i -> i).toArray())
            , "IllegalArgumentException was expected");
        assertEquals("the number of dice is not " + GameParameters.NB_DICE + " but " + listTest2.size()
            , thrown2.getMessage());

    }
}
