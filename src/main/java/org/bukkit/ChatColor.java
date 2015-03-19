package org.bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
/**
 * All supported color values for chat
 */
public enum ChatColor {
    /**
     * Represents black
     */
    BLACK('0', 0x00),
    /**
     * Represents dark blue
     */
    DARK_BLUE('1', 0x1),
    /**
     * Represents dark green
     */
    DARK_GREEN('2', 0x2),
    /**
     * Represents dark blue (aqua)
     */
    DARK_AQUA('3', 0x3),
    /**
     * Represents dark red
     */
    DARK_RED('4', 0x4),
    /**
     * Represents dark purple
     */
    DARK_PURPLE('5', 0x5),
    /**
     * Represents gold
     */
    GOLD('6', 0x6),
    /**
     * Represents gray
     */
    GRAY('7', 0x7),
    /**
     * Represents dark gray
     */
    DARK_GRAY('8', 0x8),
    /**
     * Represents blue
     */
    BLUE('9', 0x9),
    /**
     * Represents green
     */
    GREEN('a', 0xA),
    /**
     * Represents aqua
     */
    AQUA('b', 0xB),
    /**
     * Represents red
     */
    RED('c', 0xC),
    /**
     * Represents light purple
     */
    LIGHT_PURPLE('d', 0xD),
    /**
     * Represents yellow
     */
    YELLOW('e', 0xE),
    /**
     * Represents white
     */
    WHITE('f', 0xF),
    /**
     * Represents magical characters that change around randomly
     */
    MAGIC('k', 0x10, true),
    /**
     * Makes the text bold.
     */
    BOLD('l', 0x11, true),
    /**
     * Makes a line appear through the text.
     */
    STRIKETHROUGH('m', 0x12, true),
    /**
     * Makes the text appear underlined.
     */
    UNDERLINE('n', 0x13, true),
    /**
     * Makes the text italic.
     */
    ITALIC('o', 0x14, true),
    /**
     * Resets all previous chat colors or formats.
     */
    RESET('r', 0x15);

    /**
     * The special character which prefixes all chat colour codes. Use this if
     * you need to dynamically convert colour codes from your custom format.
     */
    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    /**
     * Pattern to remove all colour codes.
     */
    public static final Pattern STRIP_COLOR_PATTERN = Pattern.compile( "(?i)" + String.valueOf( COLOR_CHAR ) + "[0-9A-FK-OR]" );
    /**
     * Colour instances keyed by their active character.
     */
    private static final Map<Character, ChatColor> BY_CHAR = new HashMap<Character, ChatColor>();
    /**
     * The code appended to {@link #COLOR_CHAR} to make usable colour.
     */
    private final char code;
    /**
     * This colour's colour char prefixed by the {@link #COLOR_CHAR}.
     */
    private final String toString;
    @Getter
    private final String name;

    static
    {
        for ( ChatColor colour : values() )
        {
            BY_CHAR.put( colour.code, colour );
        }
    }

    private ChatColor(char code, String name)
    {
        this.code = code;
        this.name = name;
        this.toString = new String( new char[]
        {
            COLOR_CHAR, code
        } );
    }

    @Override
    public String toString()
    {
        return toString;
    }

    /**
     * Strips the given message of all color codes
     *
     * @param input String to strip of color
     * @return A copy of the input string, without any coloring
     */
    public static String stripColor(final String input)
    {
        if ( input == null )
        {
            return null;
        }

        return STRIP_COLOR_PATTERN.matcher( input ).replaceAll( "" );
    }

    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate)
    {
        char[] b = textToTranslate.toCharArray();
        for ( int i = 0; i < b.length - 1; i++ )
        {
            if ( b[i] == altColorChar && ALL_CODES.indexOf( b[i + 1] ) > -1 )
            {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase( b[i + 1] );
            }
        }
        return new String( b );
    }

    /**
     * Get the ChatColor represented by the specified code.
     *
     * @param code the code to search for
     * @return the mapped colour, or null if non exists
     */
    public static ChatColor getByChar(char code)
    {
        return BY_CHAR.get( code );
    }
}
