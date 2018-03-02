

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


	class GuessListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ev){
            String guess = HangmanGUI.getText();

            if(Hangman.checkUserGuess(guess)) {
                Hangman.updateSecretWord(guess);
                HangmanGUI.drawSecretWord();

                if(Hangman.lettersGuessed.size() != 0)      // No letters have been guessed by the user at the beginning
                    HangmanGUI.drawLettersGuessed();

                // Checks if the user has won or lost
                if (Hangman.checkIfWon())
                    Hangman.winSequence();
                else if (Hangman.guessesRemaining == 0)
                    Hangman.loseSequence();
            }
            
        }
    
}

