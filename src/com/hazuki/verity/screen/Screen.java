package com.hazuki.verity.screen;

import com.hazuki.verity.Verity;
import com.hazuki.verity.button.Button;
import com.hazuki.verity.font.Fonts;
import com.hazuki.verity.settings.Settings;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class Screen {

    private ArrayList<Button> buttons = new ArrayList<>();

    public Image defaultBackground;

    public Music backgroundMusic;

    public int alpha = 255;

    public void init() throws SlickException {
        this.buttons.clear();
        this.defaultBackground = new Image("assets/background.png");
        this.backgroundMusic = new Music("assets/ogg/BGMusic.ogg");
        this.backgroundMusic.loop(1.0F, Settings.musicVolume);
    }

    public void actionPerformed(int buttonID) {
    }

    public void update() {
        this.buttons.forEach(Button::update);

        if (!this.shouldLoopBGMusic()) {
            this.backgroundMusic.stop();
        }
        if (this.shouldFadeOut()) {
            if (this.alpha > 0) {
                this.alpha -= 15;
            }
        } else {
            if (this.alpha > 0) {
                this.alpha = 0;
            }
        }
    }

    public void render() {
        var fpsText = "FPS: " + Verity.getFPS();

        this.drawImage(this.defaultBackground, 0.0F, 0.0F);
        this.buttons.forEach(Button::render);

        if (this.shouldDrawFPS()) {
            this.drawString(Fonts.font25, fpsText, 1832.0F, 1051.0F, Color.black);
        }
        if (this.shouldFadeOut()) {
            this.drawRect(0, 0, Verity.DISPLAY_WIDTH, Verity.DISPLAY_HEIGHT, new Color(0, 0, 0, this.alpha));
        }
    }

    public void mouseClicked(int button, int x, int y, int clickCount) {
    }

    public void keyPressed(int key, char c) {
    }

    protected boolean shouldDrawFPS() {
        return false;
    }

    protected boolean shouldLoopBGMusic() {
        return false;
    }

    protected boolean shouldFadeOut() {
        return true;
    }

    protected void drawRect(float x, float y, float width, float height, Color color) {
        Verity.getGameGraphics().setColor(color);
        Verity.getGameGraphics().fillRect(x, y, width, height);
    }

    protected void drawImage(Image image, float x, float y) {
        Verity.getGameGraphics().drawImage(image, x, y);
    }

    protected void drawString(UnicodeFont font, String text, float x, float y, Color color) {
        font.drawString(x, y, text, color);
    }

    public ArrayList<Button> getButtons() {
        return this.buttons;
    }

}