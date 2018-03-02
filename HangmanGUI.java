
import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
    import javax.swing.*;


    public class HangmanGUI {

        // GUI
        static JFrame frame;
        static JTextField textField;
        static JLabel guessesRemainingLabel;
        static JLabel lettersGuessedLabel;
        static JLabel secretWordLabel;
        static JPanel image;
        // buildGUI - builds the GUI
        static void buildGUI(){
            SwingUtilities.invokeLater(
                    new Runnable(){
                        @Override
                        public void run(){
                            frame = new JFrame("Hangman");

                            // JLabels
                            guessesRemainingLabel = new JLabel("Guesses remaining: " + String.valueOf(Hangman.guessesRemaining));
                            lettersGuessedLabel = new JLabel("Already guessed: ");
                            secretWordLabel = new JLabel();
                            //imagelabel=new JLabel();

                            // TextField & checkButton
                            textField = new JTextField();
                            JButton checkButton = new JButton("Guess");
                            GuessListener guessListener = new GuessListener();
                            checkButton.addActionListener(guessListener);
                            textField.addActionListener(guessListener);

                            // Panel for all the labels
                            JPanel labelPanel = new JPanel();
                            labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
                            labelPanel.add(guessesRemainingLabel);
                            labelPanel.add(lettersGuessedLabel);
                            labelPanel.add(secretWordLabel);

                            // User panel
                            JPanel userPanel = new JPanel(new BorderLayout());
                            userPanel.add(BorderLayout.CENTER, textField);
                            userPanel.add(BorderLayout.EAST, checkButton);
                            int i=6-Hangman.guessesRemaining;
                            String str="C:\\Users\\Lavanya Swarna\\Documents\\NetBeansProjects\\Hangman\\src\\"+i+".jpg"; 
                            BufferedImage b = null;
                            try {
                                b = ImageIO.read(new File(str));
                            } catch (IOException ex) {
                                Logger.getLogger(HangmanGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ImageIcon d=new ImageIcon(b);
                            JLabel j=new JLabel(d);
                            JPanel p = new JPanel();
                            p.add(j);
                            //userPanel.add(p);
                            labelPanel.add(userPanel);
                             JPanel mainPanel = new JPanel();
                                mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
                                //mainPanel.add(p);
                                mainPanel.add(labelPanel);
                            mainPanel.add(p);
                            // Add everything to frame
                            frame.add(BorderLayout.CENTER, mainPanel);
                       
                            frame.setSize(250, 100);
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            frame.setVisible(true);
                        }
                    }
            );
        }

        // drawGuessesRemaining - Outputs the guesses remaining
        static void drawGuessesRemaining(){
            final String guessesMessage = "Guesses remaining: " + String.valueOf(Hangman.guessesRemaining);
            SwingUtilities.invokeLater(
                    new Runnable(){
                        @Override
                        public void run(){
                            /*try {
                                imagelabel.add(new JLabel(new imagepanel(Hangman.guessesRemaining).d));
                            } catch (IOException ex) {
                                Logger.getLogger(HangmanGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }*/
                            guessesRemainingLabel.setText(guessesMessage);
                            guessesRemainingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                        }
                    }
            );
        }

        // drawLettersGuessed - Outputs the letters guessed
        static void drawLettersGuessed(){
            StringBuilder lettersBuilder = new StringBuilder();
            for (Character el : Hangman.lettersGuessed) {
                String s = el + " ";
                lettersBuilder.append(s);
            }
            

            final String letters = lettersBuilder.toString();
            SwingUtilities.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            lettersGuessedLabel.setText("Already guessed: " + letters);
                        }
                    }
            );
        }

        // drawSecretWord - draws the secret word with dashes & etc for user to use to guess the word with
        static void drawSecretWord(){
            StringBuilder word = new StringBuilder();
            for(int i=0; i<Hangman.lettersRevealed.length; i++){

                if (Hangman.lettersRevealed[i]) {
                    String s = Hangman.secretWord.charAt(i) + " ";
                    word.append(s);
                } else {
                    word.append("_ ");
                }
            }

            final String w = word.toString();
            SwingUtilities.invokeLater(
                    new Runnable(){
                        @Override
                        public void run() {
                            secretWordLabel.setText(w);
                        }
                    }
            );
        }

        //playAgainDialog - shows the confirm w
        static int playAgainDialog(String m){
            return JOptionPane.showConfirmDialog(frame, m, "Play again?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        }


        // GETTERS
        public static String getText(){
            return textField.getText();
        }

        // SETTERS
        static void setText(final String t){
            SwingUtilities.invokeLater(
                    new Runnable() {
                        @Override
                        public void run() {
                            textField.setText(t);
                        }
                    }
            );
        }



}
