package com.liljavagame.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    private boolean clearing = false;
    // The above boolean is to account for the fact that when hitting play or try again from menu, objects are cleared from the list then access to
    // them is attempted and failed / found as null and results in null pointer exception
    // We use this in the clearEnemys() method

    LinkedList<GameObject> object = new LinkedList<>();

    // Updates all game objects
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    // Renders game objects
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            if (clearing) {
                return; // To solve Null Pointer Exception issue when hitting play button from menu, it was trying to access deleted objects
            }
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void clearEnemys() {
        clearing = true;
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.getId() == ID.Player) {
                object.clear();
                if (Game.gameState != Game.STATE.End) {
                    addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
                    // We don't use object.remove(tempObject) because indexes change when we remove objects from list
                }
                break;
            }
        }
        clearing = false;
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

}
