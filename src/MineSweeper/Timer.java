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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Amedeo
 */
public class Timer extends JPanel implements Runnable {

    private static JLabel mines;

    private final JLabel timer;
    private int currentTime;

    public Timer() {
        mines = new JLabel("Mine: " + Finestra.nMines);

        currentTime = 0;
        timer = new JLabel("Tempo: " + currentTime);

        this.add(mines);
        this.add(timer);
    }

    public static void decreaseMines(boolean choice) {
        if (choice) {
            Finestra.nMines--;
        } else {
            Finestra.nMines++;
        }

        mines.setText("Mine: " + Finestra.nMines);

        if (Finestra.nMines == 0 && Finestra.gameWin()) {                       //se non rimangono mine controlla condizioni vittoria
            JOptionPane.showMessageDialog(null, "Hai Vinto!!!", "Congratulazioni!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);                                                     //FINE!!!
        }
    }

    @Override
    public void run() {
        while (currentTime < 99999) {                                           //limite massimo di conteggio del tempo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("an Error occurred while sleeping");
            }

            timer.setText("Tempo: " + (++currentTime));
        }
    }

}
