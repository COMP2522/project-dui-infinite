package org.bcit.comp2522.dui.ui;

import org.bcit.comp2522.dui.client.*;

public class Elements {
  private ContentLoader loader;
  private Manager manager;
  private Window window;
  private Path path;

  public Elements(Window window, Manager manager, ContentLoader loader) {
    this.manager = manager;
    this.loader = loader;
    this.window = window;
  }

  public void displayScore() {
    window.textFont(loader.tinyFont);
    window.textAlign(manager.LEFT);
    window.fill(0, 0, 255);
    window.text("Score: " + manager.game.score, 850, 75); // display the score at position (, )
  }
  public void displayHighScore() {
    window.textFont(loader.tinyFont);
    window.textAlign(manager.LEFT);
    window.fill(0, 0, 255);
    window.text("High Score: " + manager.game.highScore, 850, 45); // display the high score at position (, )
  }
  public void borders() {
    window.background(0);
    window.rect(0, 600, 1280, 500); // top of the border
    window.fill(255);
    window.rect(0, 100, 1280, -500); // bottom of the border
    window.fill(255);
  }
}