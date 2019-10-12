package com.hazuki.verity.screen.screens;

import com.hazuki.verity.Verity;
import com.hazuki.verity.button.buttons.BasicButton;
import com.hazuki.verity.font.Fonts;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import java.io.File;

public class SongRemoveScreen extends SongListScreen {

    private int alpha;

    @Override
    public void init() throws SlickException {
        super.init();

        this.getButtons().add(new BasicButton(9998, "YES", 717.0F, 620.0F));
        this.getButtons().add(new BasicButton(9999, "NO", 1115.0F, 620.0F));
        this.getButtons().forEach(button -> {
            if (button.buttonID == 9998 || button.buttonID == 9999) {
                button.visible = false;
            }
        });
    }

    @Override
    public void actionPerformed(int buttonID) {
        if (buttonID == 9998) {
            if (this.selectedSong == null) {
                return;
            }
            File songFolder = this.selectedSong.getSongFolder();
            File[] files = this.selectedSong.getSongFolder().listFiles();

            if (songFolder == null) {
                return;
            }
            if (files == null) {
                return;
            }
            if (!songFolder.exists()) {
                return;
            }
            if (songFolder.isDirectory()) {
                for (File file : files) {
                    if (file != null && file.exists() && file.isFile()) {
                        if (file.delete()) {
                            if (songFolder.delete()) {
                                Log.info("The song was removed successfully");
                            }
                        }
                    }
                }
            }
            try {
                Verity.setScreen(new SongListScreen());
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        if (buttonID == 9999) {
            try {
                Verity.setScreen(new SongListScreen());
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        if (this.alpha < 150) {
            this.alpha += 5;
        }
        if (this.alpha >= 150) {
            this.getButtons().forEach(button -> button.visible = true);
        }
    }

    @Override
    public void render() {
        super.render();
        this.drawRect(0.0F, 0.0F, Verity.DISPLAY_WIDTH, Verity.DISPLAY_HEIGHT, new Color(0, 0, 0, 150));
        this.drawRect(480.0F, 240.0F, 960.0F, 600.0F, new Color(255, 255, 255, this.alpha));
        if (this.alpha >= 150) {
            this.drawString(Fonts.font50, "Are you really want to remove the song?", 532.0F, 400.0F, Color.black);
        }
        this.getButtons().forEach(button -> {
            if (button.buttonID == 9998 || button.buttonID == 9999) {
                button.render();
            }
        });
    }

    @Override
    protected boolean shouldLoopBGMusic() {
        return false;
    }

    @Override
    protected boolean shouldDrawFPS() {
        return false;
    }

    @Override
    protected boolean shouldFadeOut() {
        return false;
    }

}