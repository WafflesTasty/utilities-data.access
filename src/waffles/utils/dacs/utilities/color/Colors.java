package waffles.utils.dacs.utilities.color;

import waffles.utils.tools.Randomizer;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Longs;

/**
 * The {@code Colors} class defines utility methods to generate {@code Color} components.
 *
 * @author Waffles
 * @since Feb 10, 2020
 * @version 1.0
 */
public final class Colors
{
	/**
	 * The invisible color.
	 */
	public static final Color BLANK			= new Color(  0,   0,   0,   0);
	/**
	 * The color white.
	 */
	public static final Color WHITE 		= new Color(255, 255, 255, 255);
	/**
	 * The color light gray.
	 */
	public static final Color LIGHT_GRAY 	= new Color(192, 192, 192, 255);
	/**
	 * The color gray.
	 */
	public static final Color GRAY 			= new Color(128, 128, 128, 255);
	/**
	 * The color dark gray.
	 */
	public static final Color DARK_GRAY 	= new Color( 64,  64,  64, 255);
	/**
	 * The color black.
	 */
	public static final Color BLACK 		= new Color(  0,   0,   0, 255);
	
	/**
	 * The color red.
	 */
	public static final Color RED 			= new Color(255,   0,   0, 255);
	/**
	 * The color yellow.
	 */
	public static final Color YELLOW 		= new Color(255, 255,   0, 255);
	/**
	 * The color green.
	 */
	public static final Color GREEN 		= new Color(  0, 255,   0, 255);
	/**
	 * The color cyan.
	 */
	public static final Color CYAN 			= new Color(  0, 255, 255, 255);
	/**
	 * The color blue.
	 */
	public static final Color BLUE 			= new Color(  0,   0, 255, 255);
	/**
	 * The color magenta.
	 */
	public static final Color MAGENTA 		= new Color(255,   0, 255, 255);
	
	/**
	 * The color orange.
	 */
	public static final Color ORANGE 		= new Color(255, 200,   0, 255);
	/**
	 * The color pink.
	 */
	public static final Color PINK 			= new Color(255, 175, 175, 255);

	
	/**
	 * Adds the values of two colors together.
	 * 
	 * @param c1  a first color
	 * @param c2  a second color
	 * @return  an added color
	 */
	public static Color add(Color c1, Color c2)
	{
		float r = Floats.clamp(c1.RGBA()[0] + c2.RGBA()[0], 0f, 1f);
		float g = Floats.clamp(c1.RGBA()[1] + c2.RGBA()[1], 0f, 1f);
		float b = Floats.clamp(c1.RGBA()[2] + c2.RGBA()[2], 0f, 1f);
		float a = Floats.clamp(c1.RGBA()[3] + c2.RGBA()[3], 0f, 1f);
		
		return new Color(r, g, b, a);
	}
	
	/**
	 * Mixes the values of two colors together.
	 * 
	 * @param c1  a first color
	 * @param c2  a second color
	 * @param pc  a mix percentage
	 * @return  a mixed color
	 */
	public static Color mix(Color c1, Color c2, float pc)
	{
		float r = pc * c1.RGBA()[0] + (1.0f - pc) * c2.RGBA()[0];
		float g = pc * c1.RGBA()[1] + (1.0f - pc) * c2.RGBA()[1];
		float b = pc * c1.RGBA()[2] + (1.0f - pc) * c2.RGBA()[2];
		float a = pc * c1.RGBA()[3] + (1.0f - pc) * c2.RGBA()[3];
		
		return new Color(r, g, b, a);
	}
	
	/**
	 * Scales a color with a float value.
	 * 
	 * @param c    a color to scale
	 * @param val  a scale value
	 * @return  a scaled color
	 * 
	 * 
	 * @see Color
	 */
	public static Color scale(Color c, float val)
	{
		float r = Floats.clamp(c.RGBA()[0] * val, 0f, 1f);
		float g = Floats.clamp(c.RGBA()[1] * val, 0f, 1f);
		float b = Floats.clamp(c.RGBA()[2] * val, 0f, 1f);
		float a = c.RGBA()[3];
		
		return new Color(r, g, b, a);
	}
	
	/**
	 * Creates a color from an HSB color value.
	 * </br> Parameters are given in [0.0, 1.0].
	 * 
	 * @param h  a hue value
	 * @param s  a saturation value
	 * @param b  a brightness value
	 * @param a  an alpha value
	 * @return   a color
	 * 
	 * 
	 * @see Color
	 */
	public static Color fromHSBA(float h, float s, float b, float a)
	{
		long hex = java.awt.Color.HSBtoRGB(h, s, b) & 0xffffff;
		long alpha = Longs.clamp(Longs.round(255 * a), 0, 255);
		return new Color((hex << 8) + alpha);
	}
		
	/**
	 * Creates a color from an HSB color value.
	 * </br> Parameters are given in [0.0, 1.0].
	 * 
	 * @param h  a hue value
	 * @param s  a saturation value
	 * @param b  a brightness value
	 * @return   a color
	 * 
	 * 
	 * @see Color
	 */
	public static Color fromHSB(float h, float s, float b)
	{
		return fromHSBA(h, s, b, 1f);
	}

	/**
	 * Creates a color brighter than a previous color.
	 * 
	 * @param c  a color to brighten
	 * @param mult  a factor multiplier
	 * @return  a brighter color
	 * 
	 * 
	 * @see Color
	 */
	public static Color brighter(Color c, int mult)
	{
		float pow = Floats.pow(0.666f, mult);
		
		float r = Floats.clamp(c.RGBA()[0] / pow, 0f, 1f);
		float g = Floats.clamp(c.RGBA()[1] / pow, 0f, 1f);
		float b = Floats.clamp(c.RGBA()[2] / pow, 0f, 1f);
		float a = c.RGBA()[3];
		
		return new Color(r, g, b, a);
	}

	/**
	 * Creates a color darker than a previous color.
	 * 
	 * @param c  a color to darken
	 * @param mult  a factor multiplier
	 * @return  a darker color
	 * 
	 * 
	 * @see Color
	 */
	public static Color darker(Color c, int mult)
	{
		float pow = Floats.pow(0.666f, mult);
		
		float r = Floats.clamp(c.RGBA()[0] * pow, 0f, 1f);
		float g = Floats.clamp(c.RGBA()[1] * pow, 0f, 1f);
		float b = Floats.clamp(c.RGBA()[2] * pow, 0f, 1f);
		float a = c.RGBA()[3];
		
		return new Color(r, g, b, a);
	}
	
	/**
	 * Creates a color from an 8-digit hex string.
	 * </br> Hex codes are given as {@code 0xrrggbbaa}.
	 * 
	 * @param text  a hexadecimal string
	 * @return  a color
	 * 
	 * 
	 * @see String
	 * @see Color
	 */
	public static Color parse(String text)
	{
		return new Color(Longs.parse(text));
	}
	
	/**
	 * Returns a random {@code Color}.
	 * 
	 * @return  a random color
	 * 
	 * 
	 * @see Color
	 */
	public static Color random()
	{
		float r = Randomizer.Global().randomFloat(0, 1);
		float g = Randomizer.Global().randomFloat(0, 1);
		float b = Randomizer.Global().randomFloat(0, 1);
		return new Color(r, g, b, 1f);
	}

	
	private Colors()
	{
		// NOT APPLICABLE
	}
}