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
    
    public final static ImageIcon FLAG_ICON = new ImageIcon(new ImageIcon("./images/bomb.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)); //impostare dimensioni
    public final static ImageIcon BOMB_ICON = new ImageIcon(new ImageIcon("./images/flag.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    
    private int r,c;
    private int value;

    public Casella(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;
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
    
}
