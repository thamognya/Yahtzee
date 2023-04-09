package finalprojectpart2_tkodi1s.app;

import finalprojectpart2_tkodi1s.ai.AI;
import finalprojectpart2_tkodi1s.hand.Hand;
import finalprojectpart2_tkodi1s.score.Score;
import finalprojectpart2_tkodi1s.utilities.ShowDice;
import java.util.Scanner;

public class CLI {

    private Scanner scanner;

    public CLI(boolean ai) {
        scanner = new Scanner(System.in);
        if (ai) playAgainstAI(); else playSinglePlayer();
        return;
    }

    public CLI() {
        scanner = new Scanner(System.in);
        boolean play = true;
        int round = 0;
        System.out.println("Welcome to Yahtzee!");
        while (play) {
            System.out.println("Round: " + round);
            System.out.println("Do you want to play against the AI? (y/n)");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("y")) playAgainstAI(); else playSinglePlayer();
            System.out.println("Do you want to play again? (y/n)");
            input = scanner.nextLine().toLowerCase();
            play = input.equals("y");
        }
        System.out.println("Thanks for playing Yahtzee!");
    }

    private void playSinglePlayer() {
        Hand hand = new Hand();
        System.out.println("Initial Dice: ");
        System.out.println(hand.showDice());
        hand.changeHand();
        System.out.println("Final Dice: ");
        System.out.println(hand.showDice());
        Score score = new Score(hand.getDice());
        score.showScore();
    }

    private void playAgainstAI() {
        Hand playerHand = new Hand();
        AI ai = new AI();
        Score playerScore = new Score(playerHand.getDice()), aiScore =
            new Score(ai.rollDice());
        System.out.print("How many rounds?: ");
        int rounds = scanner.nextInt(), round = 1;
        System.out.println("Welcome to Yahtzee - Player vs AI!");
        while (round <= rounds) {
            System.out.println("-------------------");
            System.out.println("Round: " + round);
            System.out.println("Player's turn:");
            System.out.println("Initial Dice: ");
            System.out.println(playerHand.showDice());
            playerHand.changeHand();
            System.out.println("Final Dice: ");
            System.out.println(playerHand.showDice());
            playerScore = new Score(playerHand.getDice());
            playerScore.showScore();
            System.out.println("AI's turn:");
            int[] aiDice = ai.rollDice();
            System.out.println("AI rolled: \n" + ShowDice.showDice(aiDice));
            int aiCategory = ai.chooseCategory();
            ai.setScore(aiCategory, ai.getExpectedScores(aiDice)[aiCategory]);
            aiScore = new Score(aiDice);
            aiScore.showScore();
            round++;
        }
        System.out.println("-------------------");
        System.out.println("Final Scores:");
        System.out.println("Player's Score: " + playerScore.getTotalScore());
        System.out.println("AI's Score: " + aiScore.getTotalScore());
        if (
            playerScore.getTotalScore() > aiScore.getTotalScore()
        ) System.out.println("Player wins!"); else if (
            playerScore.getTotalScore() < aiScore.getTotalScore()
        ) System.out.println("AI wins!"); else System.out.println(
            "It's a tie!"
        );
    }
}
