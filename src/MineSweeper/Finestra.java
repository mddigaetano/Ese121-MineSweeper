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

        panel = new JPanel(new GridLayout(ROWS, COLUMNS));

        buttons = new Casella[ROWS + 2][COLUMNS + 2];
        int fittizia[][] = this.creaMatriceFittizia(ROWS, COLUMNS, prob);

        //Popolamento della matrice di buttoni
        for (int i = 1; i <= ROWS; i++) //sostituzione escludendo i bordi
        {
            for (int j = 1; j <= COLUMNS; j++) {
                buttons[i][j] = new Casella(i, j, fittizia[i][j]);
//                buttons[i][j].setText(buttons[i][j].getValue()+"");  //DEBUG
                this.panel.add(buttons[i][j]);
            }
        }

        Timer t = new Timer();
        Thread t1 = new Thread(t);
        t1.start();

        this.add(t, "North");
        this.add(panel, "Center");

    }

    private int[][] creaMatriceFittizia(int DIMY, int DIMX, int prob) {
        //viene inizializzata automaticamente a 0
        int matrix[][] = new int[DIMY + 2][DIMX + 2];                           //+2 per creare bordo. Permette di non strabordare

        Random rnd = new Random();

        nMines = DIMY * DIMX * prob / 100;                                      //il numero di mine è pari ad <prob>% di caselle del campo

        for (int i = 0; i < nMines; i++) {
            //inizializzazione coordinate casuali
            int tempx = rnd.nextInt(DIMX) + 1;
            int tempy = rnd.nextInt(DIMY) + 1;

            //se non c'è già una bomba
            if (matrix[tempy][tempx] != Casella.BOMB) {
                matrix[tempy][tempx] = Casella.BOMB;

                //calcolo valore caselle adiacenti
                for (int r = -1; r <= 1; r++) {                                 //dall'alto al basso
                    for (int c = -1; c <= 1; c++) {                             //da destra a sinistra
                        if ((matrix[tempy + r][tempx + c] != Casella.BOMB) && !(r == 0 && c == 0)) {  //se non è né una bomba né se stesso
                            matrix[tempy + r][tempx + c] += 1;
                        }
                    }
                }
            } else {                                                            //se nelle coordinate casuali c'è già una bomba
                i--;                                                            //ripiazza bomba corrente
            }
        }
        return matrix;
    }

    public static void gameOver() {
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLUMNS; j++) {
                if (buttons[i][j].isEnabled() && buttons[i][j].getValue() == Casella.BOMB) {
                    buttons[i][j].setIcon(Casella.BOMB_ICON);                   //mostra tutte le bombe nel gioco
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Game Over!", "GAME OVER", JOptionPane.ERROR_MESSAGE);

        System.exit(0);                                                         //FINE!!!
    }

    public static boolean gameWin() {

        boolean flag = true;

        for (int i = 1; i <= ROWS && flag; i++) {
            for (int j = 1; j <= COLUMNS && flag; j++) {
                /*
                 Le condizioni da controllare sono:
                 1)se la casella è abilitata;
                 2)se c'è una bomba non marcata;
                 3)se è marcata una caselle normale;
                 4)se non è già stata sfatata una condizione precedente
                 */
                if (buttons[i][j].isEnabled() && (((buttons[i][j].getValue() == Casella.BOMB) && buttons[i][j].getIcon() != Casella.FLAG_ICON) || ((buttons[i][j].getValue() != Casella.BOMB) && (buttons[i][j].getIcon() == Casella.FLAG_ICON))) && flag) {
                    flag = false;
                }
            }
        }

        return flag;
    }
}
