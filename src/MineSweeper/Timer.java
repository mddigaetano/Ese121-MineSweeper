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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Amedeo
 */
public class Timer extends JPanel implements Runnable{
    
    private final JLabel mines;
    private int currentMines;
    
    private final JLabel timer;
    private int currentTime;
    
    public Timer(int nMines){
        currentMines = nMines;
        mines = new JLabel("Mine: "+nMines);
        
        currentTime = 0;
        timer = new JLabel("Tempo: "+currentTime);
        
        this.add(mines);
        this.add(timer);
    }
    
    public void decreaseMines(boolean choice){
        if(choice)
            currentMines--;
        else
            currentMines++;
        
        mines.setText("Mine: "+currentMines);
    }

    @Override
    public void run() {
        while(currentTime < 99999){
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }

            timer.setText("Tempo: "+(++currentTime));
        }
    }
    
}
