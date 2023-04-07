package org.bcit.comp2522.dui.client;

import org.bcit.comp2522.dui.ui.ContentLoader;
import org.bcit.comp2522.dui.ui.UI;
import processing.core.*;

public class Manager extends PApplet {

  // UI instance, used for all ui interactions
  public UI ui;

  // game instance, used for scoring purposes
  public Game game;

  // button instance, used for menu selections and muting
  public Button button;

  // path instance (roadlines), used to manage road speed
  public Path path;

  // loader instance, used for fonts
  public ContentLoader contentLoader;

  // key input instance, used for performing actions based on pressed keys
  public KeyInput keyInput;

  // muting checker
  public boolean muted;

  // for player name
  public Menu menu;

  public int screenState;

  public int diffState;

  public int carType;

  public Manager() {
    contentLoader = new ContentLoader();
  }

  public void run(Window scene) {
    screenState = 2; // main menu
    carType = 2;
    diffState = 2; // medium

    path = new Path(this, scene); // Move this line before the UI initialization
    ui = new UI(this, contentLoader, scene);
    keyInput = new KeyInput(scene, ui.getPlayer(), this, ui.getPowerup());
    game = new Game(this);
    this.menu = new Menu(this, scene);
    button = new Button(scene, this);
    ui.init();
  }

  public void managePowerUp(PowerUp powerup) {
    if (!powerup.isActive()) {
      ui.spawnPowerUp(powerup);
      powerup.setActive(true);
    }
  }

  public UI getUi() {
    return ui;
  }
}
