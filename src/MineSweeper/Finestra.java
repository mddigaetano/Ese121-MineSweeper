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
    private JPanel panel;
    private int nMines;

    public Finestra(int dim, int prob) {
        this.setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(dim, dim));
        buttons = new Casella[dim+2][dim+2];
        int fittizia[][] = this.creaMatriceFittizia(dim, prob);
        //Popolamento della matrice di buttoni
        for(int i = 1; i <= dim; i++)
            for(int j = 1; j <= dim; j++){
                buttons[i][j] = new Casella(i, j, fittizia[i][j]);
//                buttons[i][j].setText(buttons[i][j].getValue()+"");  //DEBUG
                this.panel.add(buttons[i][j]);
            }
        
        Timer t = new Timer(nMines);
        Thread t1 = new Thread(t);
        t1.start();
        
        this.add(t,"North");
        this.add(panel, "Center");

    }
    
    private int[][] creaMatriceFittizia(int DIM, int prob){
        
        int matrix[][] = new int[DIM+2][DIM+2];
        
        Random rnd = new Random();
        
        for (int i = 1; i < DIM; i++) {
            for (int j = 1; j < DIM; j++) {
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

}
