package com.liljavagame.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();

    public Player(int x, int y, ID id) {
        super(x, y, id);

    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 50); // Stops player movement if reaches left or right of screen
        y = Game.clamp(y, 0, Game.HEIGHT - 70); // Stops player movement if reaches top or bottom of screen
    }

    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }


}
