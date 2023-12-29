package moneymanager.constants;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FontFamily {
    private final String familyName;
    private final Map<String, Font> fontVariations = new HashMap<>();

    public FontFamily(String familyName) {
        this.familyName = familyName;
    }

    public Font getFont(String style, int size){
        Font baseFont = fontVariations.get(style);
        if (baseFont == null){
            throw new IllegalArgumentException("Font Style not found!");
        }
        return baseFont.deriveFont(Font.PLAIN, size);
    }

    public void addFont(String style, String fontPath){
        try{
            File fontFile = new File(fontPath);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN);
            fontVariations.put(style, font);
        } catch (IOException e) {
            System.out.println("IOException");
            throw new RuntimeException(e);
        } catch (FontFormatException e){
            System.out.println("FontFormatException");
        }
    }

    public static void main(String[] args) {
        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Print the current working directory
        System.out.println("Current Working Directory: " + currentDirectory);
    }
}
