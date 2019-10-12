package com.hazuki.verity.font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.Log;

import java.awt.*;

public class FontLoader {

    @SuppressWarnings("unchecked")
    public static void loadFonts() {
        try {
            /* fontSize: 25 */
            Fonts.font25 = new UnicodeFont("assets/fonts/Roboto-Thin.ttf", 25, false, false);
            Fonts.font25.getEffects().add(new ColorEffect(Color.white));
            Fonts.font25.addAsciiGlyphs();
            Fonts.font25.loadGlyphs();
            /* fontSize: 35 */
            Fonts.font30 = new UnicodeFont("assets/fonts/Roboto-Thin.ttf", 30, false, false);
            Fonts.font30.getEffects().add(new ColorEffect(Color.white));
            Fonts.font30.addAsciiGlyphs();
            Fonts.font30.loadGlyphs();
            /* fontSize: 35 */
            Fonts.font35 = new UnicodeFont("assets/fonts/Roboto-Thin.ttf", 35, false, false);
            Fonts.font35.getEffects().add(new ColorEffect(Color.white));
            Fonts.font35.addAsciiGlyphs();
            Fonts.font35.loadGlyphs();
            /* fontSize: 50 */
            Fonts.font50 = new UnicodeFont("assets/fonts/Roboto-Thin.ttf", 50, false, false);
            Fonts.font50.getEffects().add(new ColorEffect(Color.white));
            Fonts.font50.addAsciiGlyphs();
            Fonts.font50.loadGlyphs();
            /* fontSize: 60 */
            Fonts.font60 = new UnicodeFont("assets/fonts/Roboto-Thin.ttf", 60, false, false);
            Fonts.font60.getEffects().add(new ColorEffect(Color.white));
            Fonts.font60.addAsciiGlyphs();
            Fonts.font60.loadGlyphs();
            // log
            Log.info("Verity - Loaded fonts");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}