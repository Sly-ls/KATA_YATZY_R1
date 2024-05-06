package org.codingdojo;

import org.codingdojo.game.DiceRoll;
import org.codingdojo.game.core.rules.Rules;
import org.codingdojo.game.yatzy.YatzyCombination;
import org.codingdojo.game.yatzy.YatzyRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class YatzyRulesTest {

    private final Rules rulesToTest = new YatzyRules();

    @Test
    void test_1s() {
        assertEquals(1, new DiceRoll(this.rulesToTest, 1, 2, 3, 4, 5).score(YatzyCombination.ONES));
        assertEquals(2, new DiceRoll(this.rulesToTest, 1, 2, 1, 4, 5).score(YatzyCombination.ONES));
        assertEquals(0, new DiceRoll(this.rulesToTest, 6, 2, 2, 4, 5).score(YatzyCombination.ONES));
        assertEquals(4, new DiceRoll(this.rulesToTest, 1, 2, 1, 1, 1).score(YatzyCombination.ONES));
    }

    @Test
    void test_2s() {
        assertEquals(4, new DiceRoll(this.rulesToTest, 1, 2, 3, 2, 6).score(YatzyCombination.TWOS));
        assertEquals(10, new DiceRoll(this.rulesToTest, 2, 2, 2, 2, 2).score(YatzyCombination.TWOS));
    }

    @Test
    void test_threes() {
        assertEquals(6, new DiceRoll(this.rulesToTest, 1, 2, 3, 2, 3).score(YatzyCombination.THREES));
        assertEquals(12, new DiceRoll(this.rulesToTest, 2, 3, 3, 3, 3).score(YatzyCombination.THREES));
    }

    @Test
    void test_fours_test() {
        assertEquals(12, new DiceRoll(this.rulesToTest, 4, 4, 4, 5, 5).score(YatzyCombination.FOURS));
        assertEquals(8, new DiceRoll(this.rulesToTest, 4, 4, 5, 5, 5).score(YatzyCombination.FOURS));
        assertEquals(4, new DiceRoll(this.rulesToTest, 4, 5, 5, 5, 5).score(YatzyCombination.FOURS));
    }

    @Test
    void test_fives() {
        assertEquals(10, new DiceRoll(this.rulesToTest, 4, 4, 4, 5, 5).score(YatzyCombination.FIVES));
        assertEquals(15, new DiceRoll(this.rulesToTest, 4, 4, 5, 5, 5).score(YatzyCombination.FIVES));
        assertEquals(20, new DiceRoll(this.rulesToTest, 4, 5, 5, 5, 5).score(YatzyCombination.FIVES));
    }

    @Test
    void test_sixes_test() {
        assertEquals(0, new DiceRoll(this.rulesToTest, 4, 4, 4, 5, 5).score(YatzyCombination.SIXES));
        assertEquals(6, new DiceRoll(this.rulesToTest, 4, 4, 6, 5, 5).score(YatzyCombination.SIXES));
        assertEquals(18, new DiceRoll(this.rulesToTest, 6, 5, 6, 6, 5).score(YatzyCombination.SIXES));
    }

    @Test
    void one_pair() {
        assertEquals(6, new DiceRoll(this.rulesToTest, 3, 4, 3, 5, 6).score(YatzyCombination.PAIR));
        assertEquals(10, new DiceRoll(this.rulesToTest, 5, 3, 3, 3, 5).score(YatzyCombination.PAIR));
        assertEquals(12, new DiceRoll(this.rulesToTest, 5, 3, 6, 6, 5).score(YatzyCombination.PAIR));
    }

    @Test
    void test_three_of_a_kind() {
        assertEquals(9, new DiceRoll(this.rulesToTest, 3, 3, 3, 4, 5).score(YatzyCombination.THREE_OF_KIND));
        assertEquals(15, new DiceRoll(this.rulesToTest, 5, 3, 5, 4, 5).score(YatzyCombination.THREE_OF_KIND));
        assertEquals(9, new DiceRoll(this.rulesToTest, 3, 3, 3, 3, 5).score(YatzyCombination.THREE_OF_KIND));
    }

    @Test
    void test_four_of_a_knd() {
        assertEquals(12, new DiceRoll(this.rulesToTest, 3, 3, 3, 3, 5).score(YatzyCombination.FOUR_OF_KIND));
        assertEquals(20, new DiceRoll(this.rulesToTest, 5, 5, 5, 4, 5).score(YatzyCombination.FOUR_OF_KIND));
        assertEquals(12, new DiceRoll(this.rulesToTest, 3, 3, 3, 3, 3).score(YatzyCombination.FOUR_OF_KIND));
    }

    @Test
    void test_yatzy() {
        assertEquals(50, new DiceRoll(this.rulesToTest, 4, 4, 4, 4, 4).score(YatzyCombination.YATZY));
        assertEquals(50, new DiceRoll(this.rulesToTest, 6, 6, 6, 6, 6).score(YatzyCombination.YATZY));
        assertEquals(0, new DiceRoll(this.rulesToTest, 6, 6, 6, 6, 3).score(YatzyCombination.YATZY));
    }

    @Test
    void two_Pair() {
        assertEquals(16, new DiceRoll(this.rulesToTest, 3, 3, 5, 4, 5).score(YatzyCombination.TWO_PAIR));
        assertEquals(16, new DiceRoll(this.rulesToTest, 3, 3, 5, 5, 5).score(YatzyCombination.TWO_PAIR));
        assertEquals(16, new DiceRoll(this.rulesToTest, 3, 3, 5, 5, 5).score(YatzyCombination.TWO_PAIR));
        assertEquals(0, new DiceRoll(this.rulesToTest, 3, 2, 5, 5, 5).score(YatzyCombination.TWO_PAIR));
        assertEquals(0, new DiceRoll(this.rulesToTest, 3, 2, 4, 5, 6).score(YatzyCombination.TWO_PAIR));
    }

    @Test
    void test_fullHouse() {
        assertEquals(18, new DiceRoll(this.rulesToTest, 6, 2, 2, 2, 6).score(YatzyCombination.FULL));
        assertEquals(22, new DiceRoll(this.rulesToTest, 2, 6, 6, 6, 2).score(YatzyCombination.FULL));
        assertEquals(0, new DiceRoll(this.rulesToTest, 2, 3, 4, 5, 6).score(YatzyCombination.FULL));
        assertEquals(0, new DiceRoll(this.rulesToTest, 2, 3, 5, 5, 6).score(YatzyCombination.FULL));
        assertEquals(0, new DiceRoll(this.rulesToTest, 2, 5, 5, 5, 6).score(YatzyCombination.FULL));
    }

    @Test
    void test_smallStraight() {
        assertEquals(15, new DiceRoll(this.rulesToTest, 1, 2, 3, 4, 5).score(YatzyCombination.SMALL_STRAIGHT));
        assertEquals(15, new DiceRoll(this.rulesToTest, 1, 3, 4, 6, 5).score(YatzyCombination.SMALL_STRAIGHT));
        assertEquals(15, new DiceRoll(this.rulesToTest, 2, 3, 4, 5, 1).score(YatzyCombination.SMALL_STRAIGHT));
        assertEquals(15, new DiceRoll(this.rulesToTest, 2, 3, 4, 5, 2).score(YatzyCombination.SMALL_STRAIGHT));
        assertEquals(0, new DiceRoll(this.rulesToTest, 1, 2, 2, 4, 5).score(YatzyCombination.SMALL_STRAIGHT));
    }

    @Test
    void test_largeStraight() {
        assertEquals(20, new DiceRoll(this.rulesToTest, 6, 2, 3, 4, 5).score(YatzyCombination.LARGE_STRAIGHT));
        assertEquals(20, new DiceRoll(this.rulesToTest, 2, 3, 4, 5, 6).score(YatzyCombination.LARGE_STRAIGHT));
        assertEquals(0, new DiceRoll(this.rulesToTest, 1, 2, 2, 4, 5).score(YatzyCombination.LARGE_STRAIGHT));
    }

    @Test
    void test_chance() {
        assertEquals(15, new DiceRoll(this.rulesToTest, 2, 3, 4, 5, 1).score(YatzyCombination.CHANCE));
        assertEquals(16, new DiceRoll(this.rulesToTest, 3, 3, 4, 5, 1).score(YatzyCombination.CHANCE));
    }
}
