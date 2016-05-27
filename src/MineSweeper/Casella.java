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

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Amedeo
 */
public class Casella extends JButton{
    
    public final static ImageIcon BOMB_ICON = new ImageIcon(new ImageIcon("./images/bomb.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)); //impostare dimensioni
    public final static ImageIcon FLAG_ICON = new ImageIcon(new ImageIcon("./images/flag.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    
    private final int r,c;
    private int value;

    public Casella(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;
        this.addActionListener(new Main());
    }
    

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public int getRow(){
        return this.r;
    }
    
    public int getColumn(){
        return this.c;
    }
    
    public void svuota(){
        
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(!(i == 0 && j == 0) && Finestra.buttons[r+i][c+j] != null && Finestra.buttons[r+i][c+j].isEnabled()){
                    Finestra.buttons[r+i][c+j].mostra();
                }
            }
        }
        
    }
    
    public void mostra(){
        
//        this.setEnabled(false);
        
        if(this.value > 0)
            this.setText(value+"");
        else if(this.value == -1)
            this.setIcon(BOMB_ICON);
        else if(this.value == 0){
            this.setEnabled(false);
            this.svuota();
        }
        
        this.setEnabled(false);
    }
    
}
