package phantom.pdbnew.ui;

import phantom.pdbnew.ui.themes.DefaultTheme;
import phantom.pdbnew.ui.themes.NeoTheme;

import java.awt.*;
import java.awt.image.BufferedImage;



public class UIUtil {
    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    public static UITheme theme = new NeoTheme();
    public static final String RESOURCES_PATH = "/Users/mishka/Documents/GitHub/phantomuserland/tools/pdbnew/resources/";
}
