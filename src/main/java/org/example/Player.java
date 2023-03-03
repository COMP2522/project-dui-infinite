package org.example;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
public class Player extends Sprite implements Collidable {
    private final float yVel = 1;
    public Player(float xPos, float yPos, float xSize, float ySize, Window window) {
        super(xPos, yPos, xSize, ySize, window);
    }

    @Override
    public void collide() {

    }
}
