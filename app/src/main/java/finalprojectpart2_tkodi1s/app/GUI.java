// package finalprojectpart2_tkodi1s.app;

// import java.awt.*;
// import java.awt.event.*;
// import java.util.*;
// import javax.swing.*;
// import finalprojectpart2_tkodi1s.hand.Hand;

// class GUIHand extends Hand {

//     private JCheckBox[] checkBoxes;
//     public boolean done = false;

//     public GUIHand() {
//         super();
//     }

//     @Override
//     public void changeHand() {
//         JFrame frame = new JFrame("Choose Dice to Keep");
//         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         frame.setSize(300, 150);
//         frame.setLocationRelativeTo(null);
//         JPanel panel = new JPanel(new GridLayout(2, 3));
//         checkBoxes = new JCheckBox[5];
//         for (int i = 0; i < 5; i++) {
//             checkBoxes[i] = new JCheckBox();
//             checkBoxes[i].setHorizontalAlignment(JCheckBox.CENTER);
//             checkBoxes[i].setSelected(false);
//             panel.add(checkBoxes[i]);
//             panel.add(new JLabel(Integer.toString(i + 1)));
//         }
//         frame.add(panel, BorderLayout.CENTER);
//         JPanel buttonPanel = new JPanel();
//         JButton okButton = new JButton("OK");
//         okButton.addActionListener(
//             new ActionListener() {
//                 public void actionPerformed(ActionEvent e) {
//                     int[] keep = new int[5];
//                     for (int i = 0; i < 5; i++) {
//                         if (checkBoxes[i].isSelected()) {
//                             keep[i] = 1;
//                         }
//                     }
//                     changeHand(keep);
//                     frame.dispose();
//                 }
//             }
//         );
//         JButton cancelButton = new JButton("Cancel");
//         cancelButton.addActionListener(
//             new ActionListener() {
//                 public void actionPerformed(ActionEvent e) {
//                     frame.dispose();
//                 }
//             }
//         );
//         buttonPanel.add(okButton);
//         buttonPanel.add(cancelButton);
//         frame.add(buttonPanel, BorderLayout.SOUTH);
//         frame.setVisible(true);
//         done = true;
//     }

//     public void changeHand(int[] keep) {
//         for (int i = 0; i < 5; i++) {
//             if (keep[i] == 0) {
//                 roll(i + 1);
//             }
//         }
//     }
// }

// public class GUI extends JFrame {

//     private JLabel messageLabel;
//     private JButton singlePlayerButton, aiPlayerButton;
//     private JPanel mainPanel;

//     public GUI() {
//         super("Yahtzee");
//         setSize(400, 200);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         messageLabel = new JLabel("Welcome to Yahtzee!", SwingConstants.CENTER);
//         singlePlayerButton = new JButton("Single Player");
//         aiPlayerButton = new JButton("Player vs AI");

//         singlePlayerButton.addActionListener(
//             new ActionListener() {
//                 public void actionPerformed(ActionEvent e) {
//                     playSinglePlayer();
//                 }
//             }
//         );

//         aiPlayerButton.addActionListener(
//             new ActionListener() {
//                 public void actionPerformed(ActionEvent e) {
//                     playAgainstAI();
//                 }
//             }
//         );

//         mainPanel = new JPanel(new GridLayout(3, 1));
//         mainPanel.add(messageLabel);
//         mainPanel.add(singlePlayerButton);
//         mainPanel.add(aiPlayerButton);

//         add(mainPanel);
//         setVisible(true);
//     }

//     private void playSinglePlayer() {
//         GUIHand hand = new GUIHand();
//         String initialDice = "Initial Dice: \n" + hand.showDice() + "\n";
//         hand.changeHand();
//         String finalDice = "Final Dice: \n" + hand.showDice() + "\n";
//         Score score = new Score(hand.getDice());
//         String scoreText = score.getScore();

//         JOptionPane.showMessageDialog(
//             null,
//             initialDice + finalDice + scoreText,
//             "Single Player",
//             JOptionPane.PLAIN_MESSAGE
//         );
//     }

//     public void playAgainstAI() {
//         GUIHand playerHand = new GUIHand();
//         AI ai = new AI();
//         Score playerScore = new Score(playerHand.getDice()), aiScore =
//             new Score(ai.rollDice());

//         // Create a JSpinner for selecting the number of rounds
//         SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1); // minimum value 1, maximum value 10, increment by 1
//         JSpinner spinner = new JSpinner(model);

//         // Show a dialog with the JSpinner and wait for the user to select a value
//         JOptionPane.showMessageDialog(
//             null,
//             spinner,
//             "Select the number of rounds",
//             JOptionPane.QUESTION_MESSAGE
//         );

//         int rounds = (int) spinner.getValue();

//         String welcomeMessage = "Welcome to Yahtzee - Player vs AI!\n";

//         for (int round = 1; round <= rounds; round++) {
//             String initialDiceMessage =
//                 "Initial Dice: \n" + playerHand.showDice() + "\n";

//             JOptionPane.showMessageDialog(
//                 null,
//                 initialDiceMessage,
//                 "Round " + round + " - Player's turn",
//                 JOptionPane.PLAIN_MESSAGE
//             );

//             playerHand.changeHand();

//             String finalDiceMessage =
//                 "Final Dice: \n" + playerHand.showDice() + "\n";

//             JOptionPane.showMessageDialog(
//                 null,
//                 finalDiceMessage,
//                 "Round " + round + " - Player's turn",
//                 JOptionPane.PLAIN_MESSAGE
//             );

//             playerScore = new Score(playerHand.getDice());

//             String scoreMessage = playerScore.getScore();

//             JOptionPane.showMessageDialog(
//                 null,
//                 scoreMessage,
//                 "Round " + round + " - Player's turn",
//                 JOptionPane.PLAIN_MESSAGE
//             );

//             int[] aiDice = ai.rollDice();

//             String aiDiceMessage =
//                 "AI rolled: \n" + Utils.showIntDice(aiDice) + "\n";

//             JOptionPane.showMessageDialog(
//                 null,
//                 aiDiceMessage,
//                 "Round " + round + " - AI's turn",
//                 JOptionPane.PLAIN_MESSAGE
//             );

//             int aiCategory = ai.chooseCategory();
//             ai.setScore(aiCategory, ai.getExpectedScores(aiDice)[aiCategory]);
//             aiScore = new Score(aiDice);

//             String aiScoreMessage = aiScore.getScore();

//             JOptionPane.showMessageDialog(
//                 null,
//                 aiScoreMessage,
//                 "Round " + round + " - AI's turn",
//                 JOptionPane.PLAIN_MESSAGE
//             );
//         }

//         String finalScoresMessage =
//             "Final Scores:\n" +
//             "Player's Score: " +
//             playerScore.getTotalScore() +
//             "\n" +
//             "AI's Score: " +
//             aiScore.getTotalScore() +
//             "\n";

//         if (playerScore.getTotalScore() > aiScore.getTotalScore()) {
//             finalScoresMessage += "Player wins!";
//         } else if (playerScore.getTotalScore() < aiScore.getTotalScore()) {
//             finalScoresMessage += "AI wins!";
//         } else {
//             finalScoresMessage += "It's a tie!";
//         }

//         JOptionPane.showMessageDialog(
//             null,
//             finalScoresMessage,
//             "Final Scores",
//             JOptionPane.PLAIN_MESSAGE
//         );
//     }

//     public static void main(String[] args) {
//         GUI gui = new GUI();
//     }
// }
