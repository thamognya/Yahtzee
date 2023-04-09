package finalprojectpart2_tkodi1s.ai;

import finalprojectpart2_tkodi1s.die.Die;
import finalprojectpart2_tkodi1s.score.Score;
import finalprojectpart2_tkodi1s.utilities.ShowDice;
import java.util.Arrays;

public class AI {

    private int[] dice, counts, expectedScores;
    public int[] scores;

    public AI() {
        dice = new int[5];
        counts = new int[6];
        scores = new int[13];
        expectedScores = new int[13];
    }

    public int[] rollDice() {
        Die dieForRolling = new Die();
        for (int i = 0; i < dice.length; i++) dice[i] = dieForRolling.roll();
        updateCounts();
        updateExpectedScores();
        return dice;
    }

    private void updateCounts() {
        Arrays.fill(counts, 0);
        for (int i = 0; i < dice.length; i++) counts[dice[i] - 1]++;
    }

    private void updateExpectedScores() {
        expectedScores = getExpectedScores(dice);
    }

    public int[] getExpectedScores(int[] dice) {
        Score score = new Score(dice);
        int[] expectedScores = new int[13];
        for (int category = 0; category < expectedScores.length; category++) {
            switch (category) {
                case 0: // Ones
                case 1: // Twos
                case 2: // Threes
                case 3: // Fours
                case 4: // Fives
                case 5: // Sixes
                    expectedScores[category] =
                        score.getUpperScore(category + 1);
                    break;
                case 6: // Three of a kind
                    expectedScores[category] = score.scoreThreeOfAKind();
                    break;
                case 7: // Four of a kind
                    expectedScores[category] = score.scoreFourOfAKind();
                    break;
                case 8: // Full house
                    expectedScores[category] = score.scoreFullHouse();
                    break;
                case 9: // Small straight
                    expectedScores[category] = score.scoreSmallStraight();
                    break;
                case 10: // Large straight
                    expectedScores[category] = score.scoreLargeStraight();
                    break;
                case 11: // Chance
                    expectedScores[category] = score.scoreChance();
                    break;
                case 12: // Yahtzee
                    expectedScores[category] = score.scoreYahtzee();
                    break;
                default:
                    expectedScores[category] = 0;
                    break;
            }
        }
        return expectedScores;
    }

    public int chooseCategory() {
        int maxIndex = 1;
        int maxExpectedScore = -1;
        for (int i = 0; i < expectedScores.length; i++) {
            if (expectedScores[i] > maxExpectedScore) {
                maxExpectedScore = expectedScores[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void setScore(int category, int score) {
        if (category >= 0 && category < scores.length) {
            scores[category] = score;
        }
    }

    public static void main(String args[]) {
        AI ai = new AI();
        for (int i = 0; i < 10; i++) {
            int[] dice = ai.rollDice();
            System.out.println("AI rolled: " + ShowDice.showDice(dice));
            int category = ai.chooseCategory();
            System.out.println("AI chose category " + category);
            ai.setScore(category, ai.getExpectedScores(dice)[category]);
        }
        System.out.println("AI scores: " + Arrays.toString(ai.scores));
    }
}
