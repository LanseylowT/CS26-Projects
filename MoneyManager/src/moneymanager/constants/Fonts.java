/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moneymanager.constants;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author donla
 */
public class Fonts {
    private final Map<String, FontFamily> fontFamilies = new HashMap<>();

    public Fonts() {
        // region Raleway
        FontFamily RALEWAY = new FontFamily("Raleway");
        RALEWAY.addFont("Regular", "src/moneymanager/fonts/raleway_regular.ttf");
        RALEWAY.addFont("Bold", "src/moneymanager/fonts/raleway/raleway_bold.ttf");
        RALEWAY.addFont("LightItalic", "src/moneymanager/fonts/raleway/raleway_light_italic.ttf");
        RALEWAY.addFont("Medium", "src/moneymanager/fonts/raleway/raleway_medium.ttf");
        RALEWAY.addFont("SemiBold", "src/moneymanager/fonts/raleway/raleway_semibold.ttf");
        fontFamilies.put("Raleway", RALEWAY);
        // endregion

        // region Montserrat
        FontFamily MONTSERRAT = new FontFamily("Montserrat");
        MONTSERRAT.addFont("Bold", "src/moneymanager/fonts/montserrat/montserrat_bold.ttf");
        MONTSERRAT.addFont("Regular", "src/moneymanager/fonts/montserrat/montserrat_regular.ttf");
        fontFamilies.put("Montserrat", MONTSERRAT);
        // endregion

    }

    public Font getFont(String fontFamily, String fontStyle, int fontSize){
        FontFamily family = fontFamilies.get(fontFamily);
        if (family != null){
            return family.getFont(fontStyle, fontSize);
        } else {
            throw new IllegalArgumentException("Font family not found: " + fontFamily);
        }
    }

    public static void main(String[] args) {
        // Get the current working directory
        String currentDirectory = System.getProperty("user.dir");

        // Print the current working directory
        System.out.println("Current Working Directory: " + currentDirectory);
    }
}
