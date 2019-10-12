package com.hazuki.verity.button.buttons;

import com.hazuki.verity.button.Button;
import com.hazuki.verity.font.Fonts;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;


public class SongButton extends Button {

    private Image songImage;

    private int alpha;

    public SongButton(int buttonID, Image songImage, String buttonText, float x, float y) {
        super(buttonID, buttonText, x, y, songImage.getWidth(), songImage.getHeight());
        this.songImage = songImage;
    }

    @Override
    public void update() {
        super.update();

        if (this.hovered) {
            if (this.alpha < 150) {
                this.alpha += 5;
            }
        } else {
            if (this.alpha > 0) {
                this.alpha -= 5;
            }
        }
    }

    @Override
    public void render() {
        super.render();

        if (!this.visible) {
            return;
        }
        var fontHeight = Fonts.font30.getHeight(this.buttonText);
        this.drawRect(this.x - 20.0F, this.y - 20.0F, this.width + 20.0F + 20.0F, this.height + 20.0F + fontHeight + 40.0F, new Color(255, 255, 255, this.alpha));
        this.drawImage(this.songImage, this.x, this.y);

        var imageWidth = this.songImage.getWidth();
        var imageHeight = this.songImage.getHeight();
        var fontWidth = Fonts.font30.getWidth(this.buttonText);
        this.drawString(Fonts.font30, this.buttonText, this.x + (imageWidth / 2.0F) - (fontWidth / 2.0F), this.y + imageHeight + 20.0F, Color.black);
    }

}