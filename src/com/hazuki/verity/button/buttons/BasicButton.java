package com.hazuki.verity.button.buttons;

import com.hazuki.verity.button.Button;
import com.hazuki.verity.font.Fonts;
import org.newdawn.slick.Color;

public class BasicButton extends Button {

    private int hoge;

    public BasicButton(int buttonID, String buttonText, float x, float y) {
        super(buttonID, buttonText, x, y, Fonts.font50.getWidth(buttonText), Fonts.font50.getHeight(buttonText));
    }

    @Override
    public void update() {
        super.update();

        if (this.hovered) {
            if (this.hoge < 100) {
                this.hoge += 5;
            }
        } else {
            if (this.hoge > 0) {
                this.hoge -= 5;
            }
        }
    }

    @Override
    public void render() {
        super.render();

        if (!this.visible) {
            return;
        }
        var fontWidth = Fonts.font50.getWidth(this.buttonText);
        var fontHeight = Fonts.font50.getHeight(this.buttonText);
        this.drawString(Fonts.font50, this.buttonText, this.x + (this.width / 2.0F) - (fontWidth / 2.0F), this.y + (this.height / 2.0F) - (fontHeight / 2.0F), new Color(this.hoge, this.hoge, this.hoge, 255));
    }

}