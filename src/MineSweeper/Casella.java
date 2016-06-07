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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Amedeo
 */
public class Casella extends JButton {

    public final static ImageIcon BOMB_ICON = new ImageIcon(new ImageIcon("./images/bomb.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)); //impostare dimensioni
    public final static ImageIcon FLAG_ICON = new ImageIcon(new ImageIcon("./images/flag.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    public final static int BOMB = -1;
    public final static int SIZE = 40;

    private final int r, c;
    private final int value;

    public Casella(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Casella clicked = (Casella) e.getSource();

                if (e.getButton() == MouseEvent.BUTTON3 && clicked.isEnabled()) { //se viene cliccato con il tasto destro ed è abilitato
                    if (clicked.getIcon() == Casella.FLAG_ICON) {               //se già marcato
                        clicked.setIcon(null);                                  //niente immagine
                        Timer.decreaseMines(false);
                    } else {                                                    //altrimenti
                        clicked.setIcon(Casella.FLAG_ICON);                     //mostra bandiera
                        Timer.decreaseMines(true);
                    }
                } else if (clicked.getIcon() != Casella.FLAG_ICON) {            //se non marcata
                    clicked.mostra();
                }
            }
        });

        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));                 //impostazione font (di default è troppo grande)
    }

    public int getValue() {
        return value;
    }

    public void svuota() {
        //scorro celle intorno
        for (int i = -1; i <= 1; i++) {                                         //dall'alto al basso
            for (int j = -1; j <= 1; j++) {                                     //da destra a sinistra
                if (Finestra.buttons[r + i][c + j] != null && Finestra.buttons[r + i][c + j].isEnabled() && !(i == 0 && j == 0)) {//se abilitata, diversa da sé ed esiste; ATTENZIONE!!! NON INVERTIRE OPERANDI! CONTROLLO null SEMPRE PER PRIMO!!!
                    Finestra.buttons[r + i][c + j].mostra();                    //WARN! Ricorsione dentro funzione (svuota, mostra, svuota...)
                }
            }
        }

    }

    public void mostra() {
        
        if(this.isEnabled()){
            this.setEnabled(false);

            if (this.value > 0) {
                this.setText(value + "");
            } else if (this.value == Casella.BOMB) {
                this.setIcon(BOMB_ICON);
                Finestra.gameOver();
            } else if (this.value == 0) {
                this.svuota();                                                  //WARN! Ricorsione dentro funzione (mostra, svuota, mostra...)
            }
        }
    }

}
