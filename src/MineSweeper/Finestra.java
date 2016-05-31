/*
 * Copyright (C) 2016 Amedeo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package MineSweeper;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Matteo
 */
public class Finestra extends JFrame {

    static Casella[][] buttons;
    private final JPanel panel;
    static int nMines = 0;
    private static int ROWS;
    private static int COLUMNS;

    public Finestra(int rows, int columns, int prob) {
        
        ROWS = rows;
        COLUMNS = columns;
        this.setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(rows, columns));
        buttons = new Casella[rows+2][columns+2];
        int fittizia[][] = this.creaMatriceFittizia(rows, columns, prob);
        //Popolamento della matrice di buttoni
        for(int i = 1; i <= rows; i++)
            for(int j = 1; j <= columns; j++){
                buttons[i][j] = new Casella(i, j, fittizia[i][j]);
//                buttons[i][j].setText(buttons[i][j].getValue()+"");  //DEBUG
                this.panel.add(buttons[i][j]);
            }
        
        Timer t = new Timer();
        Thread t1 = new Thread(t);
        t1.start();
        
        this.add(t,"North");
        this.add(panel, "Center");

    }
    
    private int[][] creaMatriceFittizia(int DIMX, int DIMY, int prob){
        
        int matrix[][] = new int[DIMX+2][DIMY+2];
        
        Random rnd = new Random();
        
        for (int i = 1; i <= DIMX; i++) {
            for (int j = 1; j <= DIMY; j++) {
                //Generazione numero random da 1 a 100 
                //E confronto con la probabilità inserita dall'utente                
                if (rnd.nextInt(100)+1 <= prob) {
                    //Se il numero generato è minore del valore prob (probabilità inserita dall'utente)
                    //Impostiamo il suo valore a -1 (bomba)
                    matrix[i][j] = -1;
                    //Incremento il numero di mine generate
                    nMines++;
                    
                    for(int r = -1; r <= 1; r++){
                        for(int c = -1; c <= 1; c++){
                            if((matrix[i+r][j+c] != -1) && !(r == 0 && c == 0)){
                                matrix[i+r][j+c] += 1;
                            }
                        }
                    }
                } 
            }
        }
                
        return matrix;
    }

    public static void gameOver(){
        for(int i = 1; i <= ROWS; i++)
            for(int j = 1; j <= COLUMNS; j++)
                if(buttons[i][j].isEnabled() && buttons[i][j].getValue() == -1)
                    buttons[i][j].setIcon(Casella.BOMB_ICON);
        
        JOptionPane.showMessageDialog(null, "Game Over!", "GAME OVER", JOptionPane.ERROR_MESSAGE);
        
        System.exit(0);
    }
    
    public static boolean gameWin(){
        
        boolean flag = true;
        
        for(int i = 1; i <= ROWS && flag; i++){
            for(int j = 1; j <= COLUMNS && flag; j++){
                if(buttons[i][j].isEnabled() && (((buttons[i][j].getValue() == -1) && buttons[i][j].getIcon() != Casella.FLAG_ICON) || ((buttons[i][j].getValue() != -1) && (buttons[i][j].getIcon() == Casella.FLAG_ICON))) && flag){
                    flag = false;
                }
            }
        }
        
        return flag;
    }
}
