package hiiragi283.material.util;

import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public abstract class HiiragiColor {

    //Minecraftのカラーコードと同じ色
    public static final Color BLACK = new Color(0x00, 0x00, 0x00);
    public static final Color DARK_BLUE = new Color(0x00, 0x00, 0xAA);
    public static final Color DARK_GREEN = new Color(0x00, 0xAA, 0x00);
    public static final Color DARK_AQUA = new Color(0x00, 0xAA, 0xAA);
    public static final Color DARK_RED = new Color(0xAA, 0x00, 0x00);
    public static final Color DARK_PURPLE = new Color(0xAA, 0x00, 0xAA);
    public static final Color GOLD = new Color(0xFF, 0xAA, 0x00);
    public static final Color GRAY = new Color(0xAA, 0xAA, 0xAA);
    public static final Color DARK_GRAY = new Color(0x55, 0x55, 0x55);
    public static final Color BLUE = new Color(0x55, 0x55, 0xFF);
    public static final Color GREEN = new Color(0x55, 0xFF, 0x55);
    public static final Color AQUA = new Color(0x55, 0xFF, 0xFF);
    public static final Color RED = new Color(0xFF, 0x55, 0x55);
    public static final Color LIGHT_PURPLE = new Color(0xFF, 0x55, 0xFF);
    public static final Color YELLOW = new Color(0xFF, 0xFF, 0x55);
    public static final Color WHITE = new Color(0xFF, 0xFF, 0xFF);

    //複数の色を混合するメソッド
    public static Color mixColor(Color... colors) {
        return mixColor(Arrays.asList(colors));
    }

    public static Color mixColor(Collection<Color> colors) {
        int averageR = 0;
        int averageG = 0;
        int averageB = 0;
        for (Color color : colors) {
            averageR += color.getRed();
            averageG += color.getGreen();
            averageB += color.getBlue();
        }
        return new Color(averageR / colors.size(), averageG / colors.size(), averageB / colors.size());
    }

    //複数の色を比率を指定して混合するメソッド
    public static Color blendColor(Map<Color, Integer> colors) {
        int averageR = 0;
        int averageG = 0;
        int averageB = 0;
        int weight = 0;
        for (Map.Entry<Color, Integer> entry : colors.entrySet()) {
            averageR += entry.getKey().getRed() * entry.getValue();
            averageG += entry.getKey().getGreen() * entry.getValue();
            averageB += entry.getKey().getBlue() * entry.getValue();
            weight += entry.getValue();
        }
        return weight == 0 ? WHITE : new Color(averageR / weight, averageG / weight, averageB / weight);
    }


    public static void setGLColor(int color) {
        float red = (color >> 16 & 255) / 255.0f;
        float green = (color >> 8 & 255) / 255.0f;
        float blue = (color & 255) / 255.0f;
        GlStateManager.color(red, green, blue, 1.0f);
    }

    public static void setGLColor(Color color) {
        setGLColor(color.getRGB());
    }

}