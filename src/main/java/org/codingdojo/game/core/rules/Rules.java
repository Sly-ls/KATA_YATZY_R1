package org.codingdojo.game.core.rules;

import org.codingdojo.game.DiceRoll;
import org.codingdojo.game.core.combination.GameCombination;

public interface Rules {
    public int score(GameCombination combination, DiceRoll diceThrow);
}
