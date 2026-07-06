package waffles.utils.dacs.utilities.color.format;

import waffles.utils.dacs.utilities.color.Color;
import waffles.utils.lang.tokens.format.regex.RegexFormat;
import waffles.utils.lang.tokens.format.regex.RegexFormat.Hints;
import waffles.utils.lang.tokens.format.regex.RegexValue;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code ColorFormat} defines a formatter for a {@code Color}.
 * As an implementation of {@code RegexFormat}, it allows the following expressions.
 * <ul>
 *  <li>{@code R,G,B,A}: Displays the resp. component as an integer value between [0, 255].</li>
 *  <li>{@code r,g,b,a}: Displays the resp. component as a hexadecimal value between [0, FF].</li>
 * </ul>
 *
 * @author Waffles
 * @since 03 Nov 2025
 * @version 1.1
 *
 * 
 * @see RegexFormat
 * @see Color
 */
public class ColorFormat extends RegexFormat<Color>
{
	/**
	 * Creates a new {@code ColorFormat}.
	 * 
	 * @param h  format hints
	 * 
	 * 
	 * @see Hints
	 */
	public ColorFormat(Hints h)
	{
		super(h);
		
		put("R", RShort());
		put("G", GShort());
		put("B", BShort());
		put("A", AShort());
		
		put("r", RHex());
		put("g", GHex());
		put("b", BHex());
		put("a", AHex());
	}
	
	/**
	 * Creates a new {@code ColorFormat}.
	 * 
	 * @param s  a format string
	 */
	public ColorFormat(String s)
	{
		this(() -> s);
	}
	
	
	RegexValue<Color> RShort()
	{
		return (c, fmt) -> toShort(c.R());
	}
	
	RegexValue<Color> GShort()
	{
		return (c, fmt) -> toShort(c.G());
	}
	
	RegexValue<Color> BShort()
	{
		return (c, fmt) -> toShort(c.B());
	}
	
	RegexValue<Color> AShort()
	{
		return (c, fmt) -> toShort(c.A());
	}
	
	RegexValue<Color> RHex()
	{
		return (c, fmt) -> toHex(c.R());
	}
	
	RegexValue<Color> GHex()
	{
		return (c, fmt) -> toHex(c.G());
	}
	
	RegexValue<Color> BHex()
	{
		return (c, fmt) -> toHex(c.B());
	}
	
	RegexValue<Color> AHex()
	{
		return (c, fmt) -> toHex(c.A());
	}
	
	short toShort(float v)
	{
		return (short) Floats.clamp(255 * v, 0, 255);
	}
	
	String toHex(float v)
	{
		String h = Integer.toHexString(toShort(v));
		return h.length() == 1 ? "0" + h : h;
	}
}
