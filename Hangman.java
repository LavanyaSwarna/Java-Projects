
import java.awt.*;
    import java.io.*;
    import java.util.*;
    import java.util.List;
    import javax.swing.*;


    public class Hangman extends JLabel {
        static String[] wordList;
        static String secretWord;
        static Set<Character> alphabet;
        static Set<Character> lettersGuessed;    // letters the user has guessed
        static boolean[] lettersRevealed;       // determines if the letter should be revealed or not
        static int guessesRemaining;



        public static void main(String[] args){
            Hangman hangman = new Hangman();
            hangman.createAlphabetSet();
            hangman.readFile();

            HangmanGUI.buildGUI();
            setUpGame();
        }



        // checkIfWon - sees if the user has won the game
        static boolean checkIfWon(){
            for(boolean isLetterShown : lettersRevealed){
                if(!isLetterShown)
                    return false;
            }
            return true;
        }

        // checkUserGuess - get input from the user
        static boolean checkUserGuess(String l){
            if(l.length() == 1 && !lettersGuessed.contains(l.charAt(0)) && alphabet.contains(l.charAt(0))) {
                HangmanGUI.setText(null);
                lettersGuessed.add(l.charAt(0));
                return true;
            }else{
                Toolkit.getDefaultToolkit().beep();
            }
            return false;
        }

        // chooseSecretWord - selects a word
        private static String chooseSecretWord(String[] wordList){
            return wordList[(int)(Math.random() * wordList.length)];
        }

        // createAlphabetSet - Creates the alphabet set that's used to ensure that the user's guess not a number nor a special character
        private void createAlphabetSet(){
            alphabet = new HashSet<Character>(26);
            for(Character c = 'a'; c<='z'; c++){
                alphabet.add(c);
            }
        }

        // loseSequence - when the the user runs out of guesses
        static void loseSequence(){
            for(int i = 0; i < lettersRevealed.length; i++){
                lettersRevealed[i] = true;
            }
            HangmanGUI.drawSecretWord();
            playAgain("Tough luck. The secret word was " + secretWord + ".\nWould you like to play another game of hangman?");
        }

        // playAgain - Allows the user to play another game of hangman
        private static void playAgain(String message){
            int ans = HangmanGUI.playAgainDialog(message);
            if(ans == JOptionPane.YES_OPTION){
                setUpGame();
            }else{
                System.exit(0);
            }
        }

        // readFile - read in wordList
        private String[] readFile(){
           /* BufferedReader input = null;
            try{
                input = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(loc)));
                wordList = input.readLine().split(" ");
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }finally{
                try {
                    if (input != null) input.close();
                }catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            }
           */
        	wordList = new String[2];
        	wordList[0]= "hello";
        	wordList[1]= "world";
            return wordList;
        }

        // setUpGame - sets up the variables appropriately
        private static void setUpGame(){
            guessesRemaining = 5;
            secretWord = chooseSecretWord(wordList);
            lettersRevealed = new boolean[secretWord.length()];
            Arrays.fill(lettersRevealed, false);
            lettersGuessed = new HashSet<Character>(26);     // 26 letters in alphabet

            HangmanGUI.drawSecretWord();
            HangmanGUI.drawLettersGuessed();
            HangmanGUI.drawGuessesRemaining();
        }

        // updateSecretWord - updates which letters of the secret word have been revealed
        static void updateSecretWord(String l){
            List<Integer> changeBool = new ArrayList<Integer>();

            if(secretWord.contains(l)){
                // Searches through secretWord & notes down all letters that equal the user's guess
                for(int i=0; i<secretWord.length(); i++){
                    if(secretWord.charAt(i) == l.charAt(0))
                        changeBool.add(i);
                }

                // Changes the boolean value for those letters @ their corresponding indexes
                for(Integer idx : changeBool)
                    lettersRevealed[idx] = true;
            }else{
                guessesRemaining--;
                HangmanGUI.drawGuessesRemaining();
            }
        }

        // winSequence - when the user has correctly guessed the secret word
        static void winSequence(){
            playAgain("Well done! You guessed " + secretWord + " with " + guessesRemaining + " guesses left!\n" +
                    "Would you like to play another game of hangman?");
        }
        

    }


