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

    Casella[][] buttons;
    JPanel panel;
    int nMine;

    public Finestra(int dim, int prob) {
        this.setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(dim, dim));
        buttons = new Casella[dim][dim];
        this.creaMatriceFittizia(dim, prob);
        //Popolamento della matrice di buttoni
//        for (int i = 0; i < dim; i++) {
//            for (int j = 0; j < dim; j++) {
//                //Generazione numero random da 1 a 100 
//                //E confronto con la probabilità inserita dall'utente                
//                if (Math.random() * (100 - 1 + 1) <= prob) {
//                    //Se il numero generato è minore del valore prob (probabilità inserita dall'utente)
//                    //Impostiamo tramite il costruttore di Casella il suo valore a -1 (bomba)
//                    panel.add(buttons[i][j] = new Casella(i, j, -1));
//                    buttons[i][j].setText("riga" + i + "-----" + "colonna" + j + "-------" + buttons[i][j].getValue());
//                    //Incremento il numero di mine generate
//                    nMine++;
//                } else //Altrimenti impostiamo tramite il costruttore di Casella il suo valore a 0 (non bomba)
//                {
//                    panel.add(buttons[i][j] = new Casella(i, j, 0));
//                }
//                buttons[i][j].setText("riga" + i + "-----" + "colonna" + j + "--------" + buttons[i][j].getValue());
//            }
//        }

//            Timer t = new Timer(nMine);
//            Thread t1 = new Thread(t);
//            t1.start();
//            this.add(t,"North");
        this.add(panel, "Center");

    }
    
    private int[][] creaMatriceFittizia(int DIM, int prob){
        
        int matrix[][] = new int[DIM+2][DIM+2];
        Random rnd = new Random();
        
        for (int i = 1; i <= DIM; i++) {
            for (int j = 1; j <= DIM; j++) {
                //Generazione numero random da 1 a 100 
                //E confronto con la probabilità inserita dall'utente                
                if (rnd.nextInt(100)+1 <= prob) {
                    //Se il numero generato è minore del valore prob (probabilità inserita dall'utente)
                    //Impostiamo il suo valore a -1 (bomba)
                    matrix[i][j] = -1;
                    //Incremento il numero di mine generate
                    nMine++;
                    
                    if()// TODO evitare mina
                        //Addizionamo 1 alle caselle vicine, in senso orario
                        matrix[i-1][j-1] += 1;
                        matrix[i-1][j] += 1;
                        matrix[i-1][j+1] += 1;
                } 
            }
        }
        
        return matrix;
    }

}
