package finalprojectpart2_tkodi1s.utilities;

import finalprojectpart2_tkodi1s.die.Die;
import java.lang.StringBuilder;

public class ShowDice {

    public static String showDice(int dice[]) {
        StringBuilder sb = new StringBuilder();
        sb.append("+------+---+---+---+---+---+\n");
        sb.append("| Dice | 1 | 2 | 3 | 4 | 5 |\n");
        sb.append("+------+---+---+---+---+---+\n");
        sb.append("| Face | ");
        for (int i = 0; i < dice.length; i++) {
            sb.append(dice[i] + " | ");
        }
        sb.append("\n+------+---+---+---+---+---+\n");
        return sb.toString();
    }

    public static String showDice(Die dice[]) {
        StringBuilder sb = new StringBuilder();
        sb.append("+------+---+---+---+---+---+\n");
        sb.append("| Dice | 1 | 2 | 3 | 4 | 5 |\n");
        sb.append("+------+---+---+---+---+---+\n");
        sb.append("| Face | ");
        for (int i = 0; i < dice.length; i++) sb.append(
            dice[i].getValue() + " | "
        );
        sb.append("\n+------+---+---+---+---+---+\n");
        return sb.toString();
    }
}
