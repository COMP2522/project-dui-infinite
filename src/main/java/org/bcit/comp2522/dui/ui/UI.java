package org.bcit.comp2522.dui.ui;

import org.bcit.comp2522.dui.client.*;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * UI controls in what orders players see content.
 * It also sets player and EnemyCar properties.
 *
 * @author Eric Tatchell
 */
public class UI implements Drawable {
    public final float[] lanes = {140, 327, 515};
    public ArrayList<PImage> cars;
    public ArrayList<EnemyCar> traffic;
    public Player player;
    public float playerWidth = 140;
    public float playerHeight = 75;
    private float slowedEnemyCarSpeed = 1.6F;
    public Menu menu;
    public Button button;
    public Manager manager;
    public Elements elements;
    private Window window;

    public UI(Manager manager, ContentLoader loader, Window scene) {
        this.manager = manager;
        this.elements = new Elements(scene, manager, loader); // Keep this one
        this.player = new Player(manager, scene, new PVector(scene.width / 5, 327), playerWidth, playerHeight);
        this.traffic = new ArrayList<EnemyCar>();
        this.cars = new ArrayList<>();
        this.button = new Button(scene, manager);
        this.menu = new Menu(manager, scene);
        this.window = scene;
        loader.loadCarImages(manager, cars);
        spawnCars(traffic);
    }
    public void uiElements() {
        window.background(0);
        manager.game.start();
        elements.borders();
        elements.displayScore();
        elements.displayHighScore();
        elements.muteButton();
        player.displayHealth();
        manager.path.drawLines();
        player.draw();
    }
    private boolean carOverlap(EnemyCar car, ArrayList<EnemyCar> traffic) {
        float minDistance = 50; // Set the minimum distance between cars here.

        for (EnemyCar otherCar : traffic) {
            if (car.checkCarOverlap(otherCar, minDistance)) {
                return true;
            }
        }
        return false;
    }


    private void spawnCars(ArrayList<EnemyCar> traffic) {
        int numCars = 2;
        int numLanes = lanes.length;
        float carSpacing = window.width / numCars;

        for (int i = 0; i < numCars; i++) {
            for (int j = 0; j < numLanes; j++) {
                float carWidth = 140;
                float carHeight = 75;
                float carSpeed = (float) (Math.random() * 8 + 7);
                float xPos = i * carSpacing + carWidth / 2 + (carSpacing / 2 * j);
                EnemyCar car = new EnemyCar(manager, window, new PVector(xPos, lanes[j]), carWidth, carHeight, carSpeed, cars);

                // Adjust the car's position until it does not overlap with any existing car
                while (carOverlap(car, traffic)) {
                    xPos += 10; // Increase the X position until there is no overlap
                    car.getPosition().x = xPos;
                }

                traffic.add(car);
            }
        }
    }

    @Override
    public void draw() {
        switch (manager.screenState) {
            case 0:
                uiElements();
                for (EnemyCar enemyCar : traffic) {
                    enemyCar.update(traffic);
                    enemyCar.draw();
                    player.check(enemyCar);
                    player.update(this);
                }
                break;
            case 1:
                manager.game.stopScore();
                menu.gameOver();
                break;
            case 2:
                menu.main();
                break;
            case 3:
                menu.menu2();
                break;
            case 4:
                menu.carSelection();
                break;
            case 5:
                menu.Leaderboard();
                break;
            case 6:
                menu.workInProgess();
                break;
        }
    }

    public void init() {
        this.draw();
    }
}
