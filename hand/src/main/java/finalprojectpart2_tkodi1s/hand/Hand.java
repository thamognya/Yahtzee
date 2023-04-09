package finalprojectpart2_tkodi1s.hand;

import finalprojectpart2_tkodi1s.die.Die;
import finalprojectpart2_tkodi1s.utilities.ShowDice;
import java.util.Scanner;

public class Hand {

    private Die[] dice;
    private Scanner scanner;

    public Hand() {
        scanner = new Scanner(System.in);
        dice = new Die[5];
        for (int i = 0; i < dice.length; i++) dice[i] = new Die();
    }

    /*
     * Returns array of integers with current values of the dice
     */
    public int[] getDice() {
        int[] values = new int[5];
        for (int i = 0; i < dice.length; i++) values[i] = dice[i].getValue();
        return values;
    }

    /* Roll all dice */
    public void rollAll() {
        for (Die die : dice) die.roll();
    }

    /* Rolls specific dice */
    public void roll(int number) {
        if (number >= 1 && number <= 5) dice[number - 1].roll();
    }

    /* Allow user to change dice by rolling two
     * more times, user can choose which dice to keep
     */
    public void changeHand() {
        int rollsRemaining = 2;
        while (rollsRemaining > 0) {
            System.out.println(this.showDice());
            System.out.print(
                "Enter die number(s) to keep (separated by a space): "
            );
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");
            int[] numbers = new int[inputs.length];
            for (int i = 0; i < inputs.length; i++) numbers[i] =
                Integer.parseInt(inputs[i]) - 1;
            for (int i = 0; i < dice.length; i++) {
                boolean keep = false;
                for (int j = 0; j < numbers.length; j++) if (i == numbers[j]) {
                    keep = true;
                    break;
                }
                if (!keep) dice[i].roll();
            }
            rollsRemaining--;
        }
    }

    /* Returns value of specified dice */
    public int get(int number) {
        if (number >= 1 && number <= 5) return dice[number - 1].getValue();
        return -1;
    }

    /* Return string representing current state of the dice in
     * the following format (or similar):
     * +------+---+---+---+---+---+
     * | Dice | 1 | 2 | 3 | 4 | 5 |
     * +------+---+---+---+---+---+
     * | Face | 4 | 2 | 4 | 6 | 1 |
     * +------+---+---+---+---+---+
     *
     */
    public String showDice() {
        return ShowDice.showDice(dice);
    }
}
