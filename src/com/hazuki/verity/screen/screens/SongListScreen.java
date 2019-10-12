package com.hazuki.verity.screen.screens;

import com.hazuki.verity.Verity;
import com.hazuki.verity.button.buttons.BasicButton;
import com.hazuki.verity.button.buttons.SongButton;
import com.hazuki.verity.font.Fonts;
import com.hazuki.verity.screen.Screen;
import com.hazuki.verity.settings.Settings;
import com.hazuki.verity.song.Song;
import com.hazuki.verity.song.SongManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class SongListScreen extends Screen {

    protected Song selectedSong;

    @Override
    public void init() throws SlickException {
        super.init();

        if (SongManager.getSongs() == null) {
            return;
        }
        SongManager.getSongs().clear();
        SongManager.loadSongs();

        var x = 50.0F;
        var y = 150.0F;

        for (int i = 0; i < SongManager.getSongs().size(); i++) {
            if (i != 0 && i % 3 == 0) {
                x = 50.0F;
                y += 400.0F;
            }
            this.getButtons().add(new SongButton(i, SongManager.getSongs().get(i).getSongImage(), SongManager.getSongs().get(i).getSongName(), x, y));
            x += 350.0F;
        }
        this.getButtons().add(new BasicButton(SongManager.getSongs().size() + 1, "PLAY", 1563.0F, 750.0F));
        this.getButtons().add(new BasicButton(SongManager.getSongs().size() + 2, "REMOVE", 1522.0F, 870.0F));
        this.getButtons().add(new BasicButton(SongManager.getSongs().size() + 3, "BACK", 50.0F, 1000.0F));
    }

    @Override
    public void actionPerformed(int buttonID) {
        for (int i = 0; i < SongManager.getSongs().size(); i++) {
            if (buttonID == i) {
                if (this.selectedSong != null) {
                    this.selectedSong.getSongFile().stop();
                }
                this.selectedSong = SongManager.getSongs().get(i);
                this.selectedSong.getSongFile().loop(1.0F, Settings.musicVolume);
            }
        }
        if (buttonID == SongManager.getSongs().size() + 1) {
        }
        if (buttonID == SongManager.getSongs().size() + 2) {
            try {
                Verity.setScreen(new SongRemoveScreen());
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
        if (buttonID == SongManager.getSongs().size() + 3) {
            try {
                Verity.setScreen(new TitleScreen());
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        super.update();

        this.getButtons().forEach(button -> {
            if (this.selectedSong != null) {
                if (button.buttonID == SongManager.getSongs().size() + 1 || button.buttonID == SongManager.getSongs().size() + 2) {
                    button.visible = true;
                }
            } else {
                if (button.buttonID == SongManager.getSongs().size() + 1 || button.buttonID == SongManager.getSongs().size() + 2) {
                    button.visible = false;
                }
            }
        });
    }

    @Override
    public void render() {
        this.drawImage(this.defaultBackground, 0.0F, 0.0F);
        this.getButtons().forEach(button -> {
            if (button.buttonID != 9998 && button.buttonID != 9999) {
                button.render();
            }
        });
        if (this.shouldFadeOut()) {
            this.drawRect(0, 0, Verity.DISPLAY_WIDTH, Verity.DISPLAY_HEIGHT, new Color(0, 0, 0, this.alpha));
        }
        this.drawRect(1320.0F, 0.0F, 600.0F, 1080.0F, new Color(255, 255, 255, 150));
        this.drawString(Fonts.font60, "SELECT SONG", 50.0F, 50.0F, Color.black);

        if (this.selectedSong != null) {
            var songImage = this.selectedSong.getSongImage();
            var songName = this.selectedSong.getSongName();
            var fontWidth = Fonts.font60.getWidth(songName);
            this.drawImage(songImage, 1470.0F, 150.0F);
            this.drawString(Fonts.font60, songName, 1320.0F + (600.0F / 2.0F) - (fontWidth / 2.0F), 560.0F, Color.black);
        }
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
        return true;
    }

}