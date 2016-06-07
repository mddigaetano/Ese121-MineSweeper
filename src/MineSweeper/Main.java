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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int diff, rows, columns;                                                //variabili temporanee per memorizzare i valori immessi da utente

        Integer options[] = {1, 2, 3, 4, 5};
        diff = (int) JOptionPane.showOptionDialog(null, "Scegli il livello di difficolta' (default: 1)", "Selezione Difficolta'", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (diff < 1 || diff > 5) //se l'opzione non è prevista
        {
            diff = 1;                                                           //imposta il valore di default
        }
        String input = (String) JOptionPane.showInputDialog(null, "Scegli le righe del campo di gioco (default: 8)", "Selezione Dimensioni", JOptionPane.QUESTION_MESSAGE, null, null, 8);
        try {                                                                   //se non viene riconosciuto un numero
            rows = Short.toUnsignedInt(Short.parseShort(input));
        } catch (Exception e) {
            rows = 8;                                                           //imposta il valore di default
        }

        input = (String) JOptionPane.showInputDialog(null, "Scegli le colonne del campo di gioco (default: 8)", "Selezione Dimensioni", JOptionPane.QUESTION_MESSAGE, null, null, 8);
        try {                                                                   //se non viene riconosciuto un numero
            columns = Short.toUnsignedInt(Short.parseShort(input));
        } catch (Exception e) {
            columns = 8;                                                        //imposta il valore di default
        }

        Finestra frame = new Finestra(rows, columns, diff * 10);                //la variabile diff viene moltiplicata per trasformare il valore simbolico in percentuale
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
