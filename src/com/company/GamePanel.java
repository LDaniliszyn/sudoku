package com.company;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 900;
    static final int BoxSize = SCREEN_HEIGHT / 9;
    static final int DELAY = 1;
    int mouseX;
    int mouseY;
    int[][][] tab = new int[9][9][3];
    boolean running = false;
    int key;
    boolean clicked = false;
    Timer timer;


    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.gray);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.addMouseListener(new MouseIListener());
        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            math();
            g.setColor(Color.darkGray);
            for (int i = 0; i < tab.length; i++) {
                for (int i1 = 0; i1 < tab.length; i1++) {
                    if (tab[i][i1][1] == 1) {
                        g.setColor(Color.red);
                    }
                    if (tab[i][i1][1] == 0) {
                        g.setColor(Color.darkGray);
                    }
                    if (mouseX < i1 * BoxSize + 95 && mouseX > i1 * BoxSize && mouseY < i * BoxSize + 95 && mouseY > i * BoxSize) {
                        g.setColor(Color.black);
                    }
                    g.fillRect(i1 * BoxSize, i * BoxSize, BoxSize - 5, BoxSize - 5);
                    g.setColor(Color.lightGray);
                    if (tab[i][i1][2] == 1) {
                        g.setColor(Color.blue);
                    }
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
                    g.drawString(String.valueOf(tab[i][i1][0]), i1 * BoxSize + 37, i * BoxSize + 57);
                }
            }
        }
    }

    public void changeValue() {
        for (int i = 0; i < tab.length; i++) {
            for (int i1 = 0; i1 < tab.length; i1++) {
                if (mouseX < i * BoxSize + 95 && mouseX > i * BoxSize && mouseY < i1 * BoxSize + 95 && mouseY > i1 * BoxSize && key != 10 && (tab[i1][i][2] != 1 || clicked)) {
                    if (clicked) {
                        tab[i1][i][2] = 1;
                    }
                    if (key == 97) {
                        tab[i1][i][0] = 1;
                        clicked = false;
                        clear();
                    }
                    if (key == 98) {
                        tab[i1][i][0] = 2;
                        clicked = false;
                        clear();
                    }
                    if (key == 99) {
                        tab[i1][i][0] = 3;
                        clicked = false;
                        clear();
                    }
                    if (key == 100) {
                        tab[i1][i][0] = 4;
                        clicked = false;
                        clear();
                    }
                    if (key == 101) {
                        tab[i1][i][0] = 5;
                        clicked = false;
                        clear();
                    }
                    if (key == 102) {
                        tab[i1][i][0] = 6;
                        clicked = false;
                        clear();
                    }
                    if (key == 103) {
                        tab[i1][i][0] = 7;
                        clicked = false;
                        clear();
                    }
                    if (key == 104) {
                        tab[i1][i][0] = 8;
                        clicked = false;
                        clear();
                    }
                    if (key == 105) {
                        tab[i1][i][0] = 9;
                        clicked = false;
                        clear();
                    }
                }
            }
        }
        key = 10;
    }

    public void clear() {
        for (int i = 0; i < tab.length; i++) {
            for (int i1 = 0; i1 < tab.length; i1++) {
                tab[i][i1][1] = 0;
            }
        }
    }

    public void move() {
        mouseX = getMousePosition().x;
        mouseY = getMousePosition().y;
    }

    public void math() {
        for (int i = 0; i < tab.length; i++) {   //  poziomy
            for (int j = 0; j < tab.length; j++) {
                for (int k = 0; k < tab.length; k++) {
                    if (tab[i][j][0] == tab[i][k][0] && j != k && tab[i][j][0] != 0 && tab[i][k][0] != 0) {
                        tab[i][j][1] = 1;
                        tab[i][k][1] = 1;
                    }

                }
            }
        }
        for (int i = 0; i < tab.length; i++) { // piony
            for (int j = 0; j < tab.length; j++) {
                for (int k = 0; k < tab.length; k++) {
                    if (tab[j][i][0] == tab[k][i][0] && j != k && tab[j][i][0] != 0 && tab[k][i][0] != 0) {
                        tab[j][i][1] = 1;
                        tab[k][i][1] = 1;
                    }

                }
            }
        }
        for (int r = 0; r < 9; r += 3) {   // sprawdzanie 3x3
            for (int t = 0; t < 9; t += 3) {
                for (int i = t; i < t + 3; i++) {
                    for (int j = r; j < r + 3; j++) {
                        for (int k = t; k < t + 3; k++) {
                            for (int l = r; l < r + 3; l++) {
                                if (tab[i][j][0] == tab[k][l][0] && (i != k || j != l) && (tab[i][j][0] != 0 || tab[k][l][0] != 0)) {
                                    tab[i][j][1] = 1;
                                    tab[k][l][1] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (running) {
            if (getMousePosition() != null) {
                move();
            }
            changeValue();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            key = e.getKeyCode();
        }
    }

    public class MouseIListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            clicked = true;
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
