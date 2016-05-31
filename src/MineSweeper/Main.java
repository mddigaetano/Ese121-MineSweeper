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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Matteo
 */
public class Main{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Integer options[] = {1,2,3,4,5};
        int diff = (int)JOptionPane.showInputDialog(null, "Scegli il livello di difficolta'", "Selezione Difficolta'", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        String input = (String)JOptionPane.showInputDialog(null, "Scegli le righe del campo di gioco", "Selezione Dimensioni", JOptionPane.QUESTION_MESSAGE, null, null, 8);
        int rows = Integer.parseInt(input);
        input = (String)JOptionPane.showInputDialog(null, "Scegli le colonne del campo di gioco", "Selezione Dimensioni", JOptionPane.QUESTION_MESSAGE, null, null, 8);
        int columns = Integer.parseInt(input);
        
        Finestra frame = new Finestra(rows, columns, diff*10);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}