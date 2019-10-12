package com.hazuki.verity.screen.screens;

import com.hazuki.verity.Verity;
import com.hazuki.verity.font.Fonts;
import com.hazuki.verity.screen.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TitleScreen extends Screen {

    private Image verityImage;

    private int alpha;

    private boolean shouldIncrement;

    @Override
    public void init() throws SlickException {
        super.init();
        this.verityImage = new Image("assets/titlemenu/Verity.png");
    }

    @Override
    public void update() {
        if (this.alpha >= 255 && this.shouldIncrement) {
            this.shouldIncrement = false;
        }
        if (this.alpha <= 50 && !this.shouldIncrement) {
            this.shouldIncrement = true;
        }
        if (this.shouldIncrement) {
            this.alpha += 5;
        }
        if (!this.shouldIncrement) {
            this.alpha -= 5;
        }
        super.update();
    }

    @Override
    public void render() {
        super.render();
        this.drawImage(this.verityImage, 722.0F, 300.0F);
        this.drawString(Fonts.font35, "Press any key to start", 798.0F, 600.0F, new Color(0, 0, 0, this.alpha));
        this.drawString(Fonts.font25, "@Hazuki - 2019", 0.0F, 1050.0F, Color.black);
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == -1) {
            return;
        }
        try {
            Verity.setScreen(new SongListScreen());
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean shouldLoopBGMusic() {
        return true;
    }

    @Override
    protected boolean shouldDrawFPS() {
        return false;
    }

    @Override
    protected boolean shouldFadeOut() {
        return true;
    }

}