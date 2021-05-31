package com.liljavagame.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

  Random r = new Random();
  Handler handler;

  public Player(int x, int y, ID id, Handler hander) {
    super(x, y, id);
    this.handler = hander;
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int)x, (int)y, 32, 32);
  }

  public void tick() {
    x += velX;
    y += velY;

    x =
        Game.clamp(
                x, 0, Game.WIDTH - 50); // Stops player movement if reaches left or right of screen
    y =
        Game.clamp(
                y, 0, Game.HEIGHT - 70); // Stops player movement if reaches top or bottom of screen

    handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.05f, handler));

    collision();
  }

  private void collision() {
    for (int i = 0; i < handler.object.size(); i++) {

      GameObject tempObject = handler.object.get(i);

      if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) { // tempobject is now enemy
        if (getBounds().intersects(tempObject.getBounds())) {
          // collision code
          HUD.HEALTH -= 2;
        }
      }
    }
  }

  public void render(Graphics g) {

    // The following isn't necessary but shows how we could draw an outline for our collistion
    // detection
    // Graphics2D g2d = (Graphics2D) g;  // Graphics2D has the method we need that Graphics doesn't
    // g.setColor(Color.red);
    // g2d.draw(getBounds()); // This will show the outline for our collision detection

    g.setColor(Color.white);
    g.fillRect((int)x, (int)y, 32, 32);
  }
}
