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
import javax.swing.*;

/**
 *
 * @author Matteo
 */
public class Finestra extends JFrame {

    Casella[][] buttons;
    JPanel panel;

    public Finestra(int dim, int prob) {
        //Variabile temporanea per la memorizzazione del numero delle mine generate
        int nMine = 0;
        this.setLayout(new BorderLayout());
        panel = new JPanel(new GridLayout(dim, dim));
        buttons = new Casella[dim][dim];
        //Popolamento della matrice di buttoni
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                //Generazione numero random da 1 a 100 
                //E confronto con la probabilità inserita dall'utente                
                if (Math.random() * (100 - 1 + 1) <= prob) {
                    //Se il numero generato è minore del valore prob (probabilità inserita dall'utente)
                    //Impostiamo tramite il costruttore di Casella il suo valore a -1 (bomba)
                    panel.add(buttons[i][j] = new Casella(i, j, -1));
                    buttons[i][j].setText("riga" + i + "-----" + "colonna" + j + "-------" + buttons[i][j].getValue());
                    //Incremento il numero di mine generate
                    nMine++;
                } else //Altrimenti impostiamo tramite il costruttore di Casella il suo valore a 0 (non bomba)
                {
                    panel.add(buttons[i][j] = new Casella(i, j, 0));
                }
                buttons[i][j].setText("riga" + i + "-----" + "colonna" + j + "--------" + buttons[i][j].getValue());
            }
        }

//            Timer t = new Timer(nMine);
//            Thread t1 = new Thread(t);
//            t1.start();
//            this.add(t,"North");
        this.add(panel, "Center");

    }

}
