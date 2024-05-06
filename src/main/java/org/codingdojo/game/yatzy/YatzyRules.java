package org.codingdojo.game.yatzy;

import org.codingdojo.game.DiceRoll;
import org.codingdojo.game.core.combination.GameCombination;
import org.codingdojo.game.core.rules.DiceRules;
import org.codingdojo.game.parameters.GameParameters;

public class YatzyRules implements DiceRules {

    public int score(GameCombination combination, DiceRoll diceThrow) {
        if (combination instanceof YatzyCombination castedCombination) {
            return switch (castedCombination) {
                case ONES -> scoreForDiceValue(1, diceThrow);
                case TWOS -> scoreForDiceValue(2, diceThrow);
                case THREES -> scoreForDiceValue(3, diceThrow);
                case FOURS -> scoreForDiceValue(4, diceThrow);
                case FIVES -> scoreForDiceValue(5, diceThrow);
                case SIXES -> scoreForDiceValue(6, diceThrow);
                case PAIR -> scoreForOccurrences(2, diceThrow);
                case THREE_OF_KIND -> scoreForOccurrences(3, diceThrow);
                case FOUR_OF_KIND -> scoreForOccurrences(4, diceThrow);
                case TWO_PAIR -> scoreTwoPairs(diceThrow);
                case FULL -> scoreFullHouse(diceThrow);
                case SMALL_STRAIGHT -> scoreSmallStraight(diceThrow);
                case LARGE_STRAIGHT -> scoreLargeStraight(diceThrow);
                case YATZY -> scoreYatzy(diceThrow);
                case CHANCE -> scoreChance(diceThrow);
            };
        } else {
            throw new IllegalArgumentException("the combination must be a YatzyCombination for this rules");
        }
    }

    /**
     * find the highest value with the number of occurences in parameters
     * with no excluded value; so we pass an impossigble value as a Parameter
     */
    private int scoreForOccurrences(int nbOccurence, DiceRoll diceThrow) {
        return scoreForOccurrences(GameParameters.MIN_DIE_VALUE - 1, nbOccurence, diceThrow);
    }

    /**
     * find the highest value with the number of occurences in parameters
     * the exclude value parameter allow checking for occurrences in the others combination (like two pair)
     */
    private int scoreForOccurrences(int exclude, int nbOccurrence, DiceRoll diceThrow) {
        int score = 0;
        if (diceThrow != null) {
            int[] counts = new int[diceThrow.getMaxValue()];
            for (int die : diceThrow.getDiceResult()) {
                counts[die - 1]++;
            }
            int maxCount = counts.length;
            for (int index = 0; index != maxCount; index++) {
                if (counts[maxCount - index - 1] >= nbOccurrence
                    && (maxCount - index) != exclude) {
                    score = (maxCount - index) * nbOccurrence;
                    break;
                }
            }
        }
        return score;
    }

    /**
     * check the number of dice with the targeted number
     */
    private int scoreForDiceValue(int target, DiceRoll diceThrow) {
        if (target < GameParameters.MIN_DIE_VALUE || target > GameParameters.MAX_DIE_VALUE) {
            throw new IllegalArgumentException("target value of "
                + target
                + " can't be reached; authorized values are between "
                + GameParameters.MIN_DIE_VALUE
                + " and " + GameParameters.MAX_DIE_VALUE);
        }
        int score = 0;
        if (diceThrow != null) {
            for (int die : diceThrow.getDiceResult()) {
                if (die == target) {
                    score += target;
                }
            }
        }
        return score;
    }

    private int scoreChance(DiceRoll diceThrow) {
        int score = 0;
        if (diceThrow != null) {
            for (int die : diceThrow.getDiceResult()) {
                score += die;
            }
        }
        return score;
    }

    private int scoreYatzy(DiceRoll diceThrow) {
        int score = scoreForOccurrences(GameParameters.NB_DICE, diceThrow);
        if (score > 0) {
            score = 50;
        }
        return score;
    }

    private int scoreTwoPairs(DiceRoll diceThrow) {
        int score = scoreForOccurrences(2, diceThrow);
        if (score > 0) {
            int toExclude = score / 2;
            int scoreSecondPair = scoreForOccurrences(toExclude, 2, diceThrow);
            if (scoreSecondPair > 0) {
                score += scoreSecondPair;
            } else {
                score = 0;
            }
        }
        return score;
    }

    private int scoreFullHouse(DiceRoll diceThrow) {
        //starting with the highest count, so we don't use a full as a pair
        int score = scoreForOccurrences(3, diceThrow);
        if (score > 0) {
            int toExclude = score / 3;
            int scorePair = scoreForOccurrences(toExclude, 2, diceThrow);
            if (scorePair > 0) {
                score += scorePair;
            } else {
                score = 0;
            }
        }
        return score;
    }

    private int scoreSmallStraight(DiceRoll diceThrow) {
        int score = 0;
        if (diceThrow != null && countContiguousElement(diceThrow.getDiceResult()) >= 4) {
            score = 15;
        }
        return score;
    }

    private int scoreLargeStraight(DiceRoll diceRoll) {
        int score = 0;

        if (diceRoll != null && countContiguousElement(diceRoll.getDiceResult()) >= 5) {
            score = 20;
        }
        return score;
    }

}



