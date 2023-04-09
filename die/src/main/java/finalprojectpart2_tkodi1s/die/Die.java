package finalprojectpart2_tkodi1s.die;

import java.util.Random;

public class Die {

    private int value;
    private Random rand;

    public Die() {
        rand = new Random();
        roll();
    }

    /* Roll a die and return its numerical value */
    public int roll() {
        value = rand.nextInt(6) + 1;
        return value;
    }

    /* Return numerical value of the die without re-rolling it */
    public int getValue() {
        return value;
    }
}
