package phantom.pdbnew.ui.system;

import phantom.pdbnew.ui.system.UITheme;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class UIUtil {

    public static String loadFile(String path) throws FileNotFoundException {
        return new Scanner(new File(path)).useDelimiter("\\Z").next();
    }

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    public static UITheme theme;
    public static final String RESOURCES_PATH = "/Users/mishka/Documents/GitHub/phantomuserland/tools/pdbnew/resources/";

    static {
        theme = loadTheme("default");
    }

    public static UITheme loadTheme(String name) {
        try {
            return UITheme.fromJSON(
                    RESOURCES_PATH+"themes/"+name+"/",
                    loadFile(RESOURCES_PATH+"themes/"+name+"/theme.json"),
                    loadFile(RESOURCES_PATH+"themes/"+name+"/styles.json")
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ImageIcon newIcon(String path) {
        return new ImageIcon(getScaledImage(new ImageIcon(path).getImage(), 32, 32));
    }

}
