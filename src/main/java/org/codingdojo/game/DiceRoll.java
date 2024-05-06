package org.codingdojo.game;

import org.codingdojo.game.core.combination.GameCombination;
import org.codingdojo.game.core.rules.Rules;
import org.codingdojo.game.parameters.GameParameters;

public class DiceRoll {

    private int[] dice;
    private final Rules rules;

    //constructor
    public DiceRoll(Rules rules, int... dice) {
        this.rules = rules;
        initDice(dice);
    }

    //CLASS METHODS
    private void initDice(int... diceParam) {
        if (diceParam.length != GameParameters.NB_DICE) {
            throw new IllegalArgumentException("the number of dice is not " + GameParameters.NB_DICE + " but " + diceParam.length);
        }

        this.dice = new int[diceParam.length];
        int index = 0;
        StringBuilder wrongValues = new StringBuilder();
        for (int die : diceParam) {
            if (die > GameParameters.MAX_DIE_VALUE
                || die < GameParameters.MIN_DIE_VALUE) {
                if (!wrongValues.isEmpty()) {
                    wrongValues.append(",");
                }
                wrongValues.append(die);
            }
            this.dice[index] = die;
            index++;
        }

        if (!wrongValues.isEmpty()) {
            String finalError = "the following value are not between " +
                GameParameters.MIN_DIE_VALUE + " and " +
                GameParameters.MAX_DIE_VALUE + " : " +
                wrongValues;
            throw new IllegalArgumentException(finalError);
        }
    }

    public int score(GameCombination combination) {
        return this.rules.score(combination, this);
    }

    //SPECIAL GETTER
    public int getMaxValue() {
        int max = 0;
        for (int die : this.dice) {
            if (die > max) {
                max = die;
            }
        }
        return max;
    }

    //GETTER && SETTER
    public int[] getDiceResult() {
        return this.dice;
    }
}
