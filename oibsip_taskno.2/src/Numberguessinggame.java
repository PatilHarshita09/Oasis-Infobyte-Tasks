import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.util.Random;
public class Numberguessinggame{
    public static void main(String []args){
        UIManager.put("OptionPane.background",Color.yellow);
        UIManager.put("Panel.background",Color.orange);
        UIManager.put("OptionPane.messageFont",new java.awt.Font("Arial", Font.BOLD,30));
        UIManager.put("OptionPane.buttonFont",new java.awt.Font("Arial", Font.BOLD,30));
        UIManager.put("OptionPane.messageForeground",Color.black);

        //range of the mystery number is set and the maximum guesses is set
        int min =1;
        int max =100;
        int i;
        int maxguesses =10;//maximum guesses 10

        //the random mystery number is generated and score is inizialized
        Random r =new Random();
        int mysterynum = r.nextInt(max)+1;
        int score =0;

        //the instruction of game is displayed
        JOptionPane.showMessageDialog(null,"----------THE MYSTERY NUMBER GUESSING GAME----------\n\n"+
                "*)THERE ARE "+maxguesses+" GUESSES IN YOUR POCKET\n\n*)GUESS THE MYSTERY NUMBER BETWEEN "+min+ " TO "+max+"\n\n\n\n GET SET BEGIN!!!    ");

        //for loop for guess the number
        for(i=1;i<=maxguesses;i++) {
            //displaying to ask the user to guess the mystery number in dialogbox
            String guessString = JOptionPane.showInputDialog("*) REMAINING GUESSES : " + i + "\n\n\n\n" + " GUESS THE NUMBER BETWEEN " + min + " to " + max + "\n\n ");


            //try and catch is used to check the user integer input and displays exception if is number not in range(1-100) or invalid format
            int guess;
            try {
                guess = Integer.parseInt(guessString);
            } catch (NumberFormatException e) {
                UIManager.put("OptionPane.messageForeground",Color.red);
                JOptionPane.showMessageDialog(null, "\n WRONG INPUT!!\n\nENTER THE NUMBER BETWEEN " + min + " to " + max+"\n\n");
                i--;
                break;
            }

            //comparing the userguess with the mystery number and displaying the result
            if (guess == mysterynum) {
                score = maxguesses - i + 1;
                JOptionPane.showMessageDialog(null, "BRAVO!!YOU GUESS THE MYSTERY NUMBER \n\n *) YOUR SCORE IS: " + score + "\n *) YOU GOT THE MYSTERY NUMBER IN " + i + " GUESSES");
            } else if (guess > mysterynum) {
                JOptionPane.showMessageDialog(null, "\nMYSTERY NUMBER IS LOWER THAN YOUR GUESS!!.\n\nTRY AGAIN.\n\n");
            } else {
                JOptionPane.showMessageDialog(null, "MYSTERY NUMBER IS HIGHER THAN YOUR GUESS!TRY AGAIN.");
            }
        }//for loop ended

        //displays if user failed to guess the mystery number
        if(score==0){UIManager.put("OptionPane.messageForeground",Color.black);
            JOptionPane.showMessageDialog(null,"GAME OVER:( \n BETTER LUCK NEXTTIME!\n\nTHE MYSTERY NUMBER WAS: "+mysterynum+" !!!");
        }

        System.exit(0);

    }

}

