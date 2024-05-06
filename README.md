Yatzy Refactoring Kata Java Version
===================================

For full instructions see [top level instructions](../README.md)

There are three variants of this kata each with different refactoring challenges.

## Code Reading Practice
Here is a list of github urls of all the YatzyCalculator Java classes:

* [Yatzy1](https://github.com/emilybache/Yatzy-Refactoring-Kata/blob/main/java/src/main/java/org/codingdojo/yatzy1/Yatzy1.java)
* [Yatzy2](https://github.com/emilybache/Yatzy-Refactoring-Kata/blob/main/java/src/main/java/org/codingdojo/yatzy2/Yatzy2.java)
* [Yatzy3](https://github.com/emilybache/Yatzy-Refactoring-Kata/blob/main/java/src/main/java/org/codingdojo/yatzy3/Yatzy3.java)


## How I handled this refactoring kata
* first thing was to make generic the specific function relative to scoring, so the complex combinations (like fullhouse or pair) were only assembling the previous function<br>
The functions "scoreForDiceValue" was made to look for a target  dice value, then the function "scoreForOccurrences" was made in order to get a score for a minimum number of occurrences, looking for highest dice value first.<br>
After that all the others combinations are just a specific number of occurrences.<br><br>

* second was to split responsibility between rules and rolls, so roll could be checked and sanitized<br>
The GameParameters handles the range for which a die roll is considered valid; values will be checked during the instantiation<br>
Rules will have a called method from the DiceRoll, so you can analyze the score without altering the result

* third, we needed to make the rules interchangeable, so any new rules set would be easy to handle <br>
To implement a new rules set, you declare a new list of scoring combination and a way to handle it.
Any combination will implement GameCombination, which is a parameter for the base method of scoring in the Rules interface Rules

## What I would do next
I believe that the next step would be to separate the rules from the dice roll and making a GameManager class handling the instantiation of the roll, then calling a specific rule instance to score the roll<br>
Then making player, owning the role, and playing by the rules; the game manager would be the one calling player to roll then counting the score.