package com.liljavagame.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu) {
            // Play button
            if (mouseOver(mx, my, 210, 130, 200, 64)) {
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler)); // Adds Player object to center of screen
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }

            // Help button
            if (mouseOver(mx, my, 210, 230, 200, 64)) {
                game.gameState = Game.STATE.Help;
            }

            // Quit button
            if (mouseOver(mx, my, 210, 330, 200, 64)) {
                System.exit(1);
            }
        }

        // Back button for help
        if (game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 210, 330, 200, 64)) {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("aria'", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 245, 75);

            g.setFont(fnt2);
            g.drawRect(210, 130, 200, 64);
            g.drawString("Play", 280, 172);


            g.drawRect(210, 230, 200, 64);
            g.drawString("Help", 280, 272);

            g.drawRect(210, 330, 200, 64);
            g.drawString("Quit", 280, 372);
        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("aria'", 1, 30);
            Font fnt3 = new Font("aria'", 1, 20);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 255, 75);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies.", 63, 200);

            g.setFont(fnt2);
            g.drawRect(210, 330, 200, 64);
            g.drawString("Back", 277, 372);
        }


    }
}

