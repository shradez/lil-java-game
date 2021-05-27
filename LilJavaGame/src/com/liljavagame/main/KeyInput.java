package com.liljavagame.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // key events for Player 1
                if (key == KeyEvent.VK_W) tempObject.setVelY(-5); // Moves Player up when pressing W
                if (key == KeyEvent.VK_S) tempObject.setVelY(5); // Down when pressing S
                if (key == KeyEvent.VK_D) tempObject.setVelX(5); // Right when pressing D
                if (key == KeyEvent.VK_A) tempObject.setVelX(-5); // Left when pressing A

            }
            if (tempObject.getId() == ID.Player2) {
                // key events for Player 2
                if (key == KeyEvent.VK_UP) tempObject.setVelY(-5); // Moves Player 2 up when pressing W
                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(5); // Down when pressing S
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(5); // Right when pressing D
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(-5); // Left when pressing A
            }

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // key events for Player 1
                if (key == KeyEvent.VK_W) tempObject.setVelY(0); // Stops Player movement when key is released
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) tempObject.setVelX(0);

            }
            if (tempObject.getId() == ID.Player2) {
                // key events for Player 2
                if (key == KeyEvent.VK_UP) tempObject.setVelY(0); // Stops Player 2 movement when key is released
                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
            }

        }
    }
}
