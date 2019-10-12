package com.hazuki.verity.button;

import com.hazuki.verity.Verity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.UnicodeFont;

public class Button {

    public int buttonID;

    protected String buttonText;

    public float x, y;

    public float width, height;

    public boolean hovered;

    public boolean visible;

    public Button(int buttonID, String buttonText, float x, float y, float width, float height) {
        this.buttonID = buttonID;
        this.buttonText = buttonText;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hovered = false;
        this.visible = true;
    }

    public void update() {
    }

    public void render() {
    }

    protected void drawRect(float x, float y, float width, float height, Color color) {
        // 矩形の色を設定
        Verity.getGameGraphics().setColor(color);
        // 矩形を描画
        Verity.getGameGraphics().fillRect(x, y, width, height);
    }

    protected void drawImage(Image image, float x, float y) {
        // 画像を描画
        Verity.getGameGraphics().drawImage(image, x, y);
    }

    protected void drawString(UnicodeFont font, String text, float x, float y, Color color) {
        //　文字列を描画
        font.drawString(x, y, text, color);
    }

}