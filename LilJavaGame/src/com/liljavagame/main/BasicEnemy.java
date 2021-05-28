package com.liljavagame.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);

        velX = 5;
        velY = 5;
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1; // If object reached top or bottom of screen, reverse velocity
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1; // If object reaches left or right of screen, reverse velocity
    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
