package com.hazuki.verity;

import com.hazuki.verity.font.FontLoader;
import com.hazuki.verity.screen.Screen;
import com.hazuki.verity.screen.screens.TitleScreen;
import org.newdawn.slick.*;
import org.newdawn.slick.util.Log;

public class Verity extends BasicGame {

    public static Screen currentScreen = new TitleScreen();

    public static int DISPLAY_WIDTH = 1920;

    public static int DISPLAY_HEIGHT = 1080;

    private static boolean DISPLAY_FULLSCREEN = true;

    private static int TARGET_FPS = 60;

    private static Graphics gameGraphics = new Graphics();

    private static int FPS;

    private Verity(String title) {
        super(title);
    }

    public static void main(String[] args) {
        try {
            var app = new AppGameContainer(new Verity("Verity"));

            app.setDisplayMode(Verity.DISPLAY_WIDTH, Verity.DISPLAY_HEIGHT, Verity.DISPLAY_FULLSCREEN);
            app.setTargetFrameRate(Verity.TARGET_FPS);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Verity.gameGraphics = gameContainer.getGraphics();
        Verity.currentScreen.init();
        FontLoader.loadFonts();

        Log.info("Verity - Finished initializing");
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Verity.FPS = gameContainer.getFPS();
        Verity.currentScreen.update();

        var mouseX = gameContainer.getInput().getMouseX();
        var mouseY = gameContainer.getInput().getMouseY();

        Verity.currentScreen.getButtons().forEach(button -> button.hovered = mouseX >= button.x && mouseX <= button.x + button.width && mouseY >= button.y && mouseY <= button.y + button.height);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        Verity.currentScreen.render();
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        Verity.currentScreen.mouseClicked(button, x, y, clickCount);
        Verity.currentScreen.getButtons().forEach(screenButton -> {
            if (button == 0 && screenButton.visible && screenButton.hovered) {
                Verity.currentScreen.actionPerformed(screenButton.buttonID);
            }
        });
    }

    @Override
    public void keyPressed(int key, char c) {
        Verity.currentScreen.keyPressed(key, c);
    }

    public static void setScreen(Screen newScreen) throws SlickException {
        Verity.currentScreen = newScreen;
        Verity.currentScreen.init();
    }

    public static Graphics getGameGraphics() {
        return Verity.gameGraphics;
    }

    public static int getFPS() {
        return Verity.FPS;
    }

}