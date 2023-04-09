package finalprojectpart2_tkodi1s.score;

import java.lang.StringBuilder;

public class Score {

    private int[] dice;

    private int[] countDice() {
        int[] counts = new int[6];
        for (int i = dice.length - 1; i >= 0; i--) {
            int value = dice[i];
            counts[value - 1]++;
        }
        return counts;
    }

    private int sumDice() {
        int sum = 0;
        for (int i = 0; i < dice.length; i++) sum += dice[i];
        return sum;
    }

    public Score(int[] dice) {
        this.dice = dice;
    }

    public int getUpperScore(int value) {
        int sum = 0;
        for (int i = 0; i < dice.length; i++) sum +=
            ((dice[i] == value) ? value : 0);
        return sum;
    }

    public int scoreThreeOfAKind() {
        int[] counts = this.countDice();
        for (int i = 0; i < counts.length; i++) if (
            counts[i] >= 3
        ) return this.sumDice();
        return 0;
    }

    public int scoreFourOfAKind() {
        int[] counts = this.countDice();
        for (int i = 0; i < counts.length; i++) if (
            counts[i] >= 4
        ) return this.sumDice();
        return 0;
    }

    public int scoreFullHouse() {
        int[] counts = this.countDice();
        boolean hasThreeOfAKind = false, hasPair = false;
        for (int i = 0; i < counts.length; i++) if (
            counts[i] == 3
        ) hasThreeOfAKind = true; else if (counts[i] == 2) hasPair = true;
        if (hasThreeOfAKind && hasPair) return 25;
        return 0;
    }

    public int scoreSmallStraight() {
        int[] counts = this.countDice();
        return (
            (
                    (
                        counts[0] >= 1 &&
                        counts[1] >= 1 &&
                        counts[2] >= 1 &&
                        counts[3] >= 1
                    ) ||
                    (
                        counts[1] >= 1 &&
                        counts[2] >= 1 &&
                        counts[3] >= 1 &&
                        counts[4] >= 1
                    ) ||
                    (
                        counts[2] >= 1 &&
                        counts[3] >= 1 &&
                        counts[4] >= 1 &&
                        counts[5] >= 1
                    )
                )
                ? 30
                : 0
        );
    }

    public int scoreLargeStraight() {
        int[] counts = this.countDice();
        return (
            (
                    (
                        counts[0] == 1 &&
                        counts[1] == 1 &&
                        counts[2] == 1 &&
                        counts[3] == 1 &&
                        counts[4] == 1
                    ) ||
                    (
                        counts[1] == 1 &&
                        counts[2] == 1 &&
                        counts[3] == 1 &&
                        counts[4] == 1 &&
                        counts[5] == 1
                    )
                )
                ? 40
                : 0
        );
    }

    public int scoreChance() {
        return this.sumDice();
    }

    public int scoreYahtzee() {
        int[] counts = countDice();
        for (int i = 0; i < counts.length; i++) if (counts[i] == 5) return 50;
        return 0;
    }

    public int scoreBonusYahtzee() {
        return this.scoreYahtzee() * 100;
    }

    public void showScore() {
        System.out.println("Score:");
        System.out.println("             1s: " + this.getUpperScore(1));
        System.out.println("             2s: " + this.getUpperScore(2));
        System.out.println("             3s: " + this.getUpperScore(3));
        System.out.println("             4s: " + this.getUpperScore(4));
        System.out.println("             5s: " + this.getUpperScore(5));
        System.out.println("             6s: " + this.getUpperScore(6));
        System.out.println("Three Of A Kind: " + this.scoreThreeOfAKind());
        System.out.println(" Four Of A Kind: " + this.scoreFourOfAKind());
        System.out.println("     Full House: " + this.scoreFullHouse());
        System.out.println(" Small Straight: " + this.scoreSmallStraight());
        System.out.println(" Large Straight: " + this.scoreLargeStraight());
        System.out.println("         Chance: " + this.scoreChance());
        System.out.println("        Yahtzee: " + this.scoreYahtzee());
        System.out.println("  Bonus Yahtzee: " + this.scoreBonusYahtzee());
    }

    public String getScore() {
        StringBuilder sb = new StringBuilder();
        sb.append("Score:\n");
        sb
            .append("             1s: ")
            .append(this.getUpperScore(1))
            .append("\n");
        sb
            .append("             2s: ")
            .append(this.getUpperScore(2))
            .append("\n");
        sb
            .append("             3s: ")
            .append(this.getUpperScore(3))
            .append("\n");
        sb
            .append("             4s: ")
            .append(this.getUpperScore(4))
            .append("\n");
        sb
            .append("             5s: ")
            .append(this.getUpperScore(5))
            .append("\n");
        sb
            .append("             6s: ")
            .append(this.getUpperScore(6))
            .append("\n");
        sb
            .append("Three Of A Kind: ")
            .append(this.scoreThreeOfAKind())
            .append("\n");
        sb
            .append(" Four Of A Kind: ")
            .append(this.scoreFourOfAKind())
            .append("\n");
        sb
            .append("     Full House: ")
            .append(this.scoreFullHouse())
            .append("\n");
        sb
            .append(" Small Straight: ")
            .append(this.scoreSmallStraight())
            .append("\n");
        sb
            .append(" Large Straight: ")
            .append(this.scoreLargeStraight())
            .append("\n");
        sb.append("         Chance: ").append(this.scoreChance()).append("\n");
        sb.append("        Yahtzee: ").append(this.scoreYahtzee()).append("\n");
        sb
            .append("  Bonus Yahtzee: ")
            .append(this.scoreBonusYahtzee())
            .append("\n");
        return sb.toString();
    }

    public int getTotalScore() {
        int upperScore =
            getUpperScore(1) +
            getUpperScore(2) +
            getUpperScore(3) +
            getUpperScore(4) +
            getUpperScore(5) +
            getUpperScore(6);
        int bonusYahtzeeScore = scoreBonusYahtzee();
        int yahtzeeScore = scoreYahtzee();
        int lowerScore =
            scoreThreeOfAKind() +
            scoreFourOfAKind() +
            scoreFullHouse() +
            scoreSmallStraight() +
            scoreLargeStraight() +
            scoreChance();
        int totalScore =
            upperScore + bonusYahtzeeScore + yahtzeeScore + lowerScore;

        if (yahtzeeScore > 0 && bonusYahtzeeScore == 0) System.out.println(
            "You scored a Yahtzee!"
        ); else if (bonusYahtzeeScore > 0) System.out.println(
            "You scored a bonus Yahtzee!"
        );

        return totalScore;
    }

    public static void main(String args[]) {
        // test getUpperScore
        Score score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.getUpperScore(1) == 1);
        assert (score.getUpperScore(2) == 2);
        assert (score.getUpperScore(3) == 3);
        assert (score.getUpperScore(4) == 4);
        assert (score.getUpperScore(5) == 5);
        assert (score.getUpperScore(6) == 0);

        // test scoreThreeOfAKind
        score = new Score(new int[] { 1, 1, 1, 4, 5 });
        assert (score.scoreThreeOfAKind() == 12);
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreThreeOfAKind() == 0);

        // test scoreFourOfAKind
        score = new Score(new int[] { 1, 1, 1, 1, 5 });
        assert (score.scoreFourOfAKind() == 9);
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreFourOfAKind() == 0);

        // test scoreFullHouse
        score = new Score(new int[] { 1, 1, 2, 2, 2 });
        assert (score.scoreFullHouse() == 25);
        score = new Score(new int[] { 1, 1, 1, 2, 2 });
        assert (score.scoreFullHouse() == 25);
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreFullHouse() == 0);

        // test scoreSmallStraight
        score = new Score(new int[] { 1, 2, 3, 4, 4 });
        assert (score.scoreSmallStraight() == 30);
        score = new Score(new int[] { 1, 2, 3, 5, 6 });
        assert (score.scoreSmallStraight() == 0);
        score = new Score(new int[] { 1, 2, 3, 4, 6 });
        assert (score.scoreSmallStraight() == 30);
        score = new Score(new int[] { 1, 2, 3, 4, 1 });
        assert (score.scoreSmallStraight() == 30);
        score = new Score(new int[] { 1, 2, 3, 4, 2 });
        assert (score.scoreSmallStraight() == 30);
        score = new Score(new int[] { 1, 2, 2, 4, 5 });
        assert (score.scoreSmallStraight() == 0);

        // test scoreLargeStraight
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreLargeStraight() == 40);
        score = new Score(new int[] { 2, 3, 4, 5, 6 });
        assert (score.scoreLargeStraight() == 40);
        score = new Score(new int[] { 1, 2, 3, 4, 4 });
        assert (score.scoreLargeStraight() == 0);

        // test scoreChance
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreChance() == 15);
        score = new Score(new int[] { 1, 1, 1, 1, 1 });
        assert (score.scoreChance() == 5);
        score = new Score(new int[] { 6, 6, 6, 6, 6 });
        assert (score.scoreChance() == 30);

        // test scoreYahtzee
        score = new Score(new int[] { 1, 1, 1, 1, 1 });
        assert (score.scoreYahtzee() == 50);
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreYahtzee() == 0);
        score = new Score(new int[] { 3, 3, 3, 3, 3 });
        assert (score.scoreYahtzee() == 50);

        // test scoreBonusYahtzee
        score = new Score(new int[] { 1, 1, 1, 1, 1 });
        assert (score.scoreBonusYahtzee() == 5000);
        score = new Score(new int[] { 1, 2, 3, 4, 5 });
        assert (score.scoreBonusYahtzee() == 0);
        score = new Score(new int[] { 3, 3, 3, 3, 3 });
        assert (score.scoreBonusYahtzee() == 5000);
    }
}
