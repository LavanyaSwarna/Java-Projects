
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lavanya Swarna
 */
public class imagepanel extends JPanel{
    ImageIcon d;
    
    imagepanel(int i) throws IOException{
        i=6-Hangman.guessesRemaining;
        String str="C:\\Users\\Lavanya Swarna\\Documents\\NetBeansProjects\\Hangman\\src\\"+i+".jpg"; 
        BufferedImage b=ImageIO.read(new File(str));
        d=new ImageIcon(b);        
        
    }
    
}
