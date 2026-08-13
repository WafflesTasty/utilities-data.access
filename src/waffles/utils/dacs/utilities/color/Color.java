package waffles.utils.dacs.utilities.color;

import waffles.utils.alg.utilities.affine.Affine;
import waffles.utils.dacs.utilities.color.format.ColorFormat;
import waffles.utils.geom.shapes.points.Point;
import waffles.utils.lang.tokens.Token;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.sets.indexed.array.set.FloatSet;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Floats;
import waffles.utils.tools.primitives.Integers;
import waffles.utils.tools.primitives.Longs;

/**
 * A {@code Color} implements a 24-bit color as a {@code FloatSet}.
 * 
 * @author Waffles
 * @since Sep 12, 2016
 * @version 1.0
 * 
 * 
 * @see Affine
 * @see FloatSet
 * @see Token
 */
public class Color implements Affine, FloatSet, Token
{	
	/**
	 * Defines a default hexadecimal format for a {@code Color}.
	 */
	public static final String DEF_FORMAT = "0x§r§§g§§b§§a§";
	
	
	private float[] rgba;
	
	/**
	 * Creates a new {@code Color}.
	 * Parameters are given in the range of [0.0, 1.0].
	 * 
	 * @param r  a  red   percentage
	 * @param g  a  green percentage
	 * @param b  a  blue  percentage
	 * @param a  an alpha percentage
	 */
	public Color(float r, float g, float b, float a)
	{
		rgba = new float[]
		{
			Floats.clamp(r, 0f, 1f),
			Floats.clamp(g, 0f, 1f),
			Floats.clamp(b, 0f, 1f),
			Floats.clamp(a, 0f, 1f)
		};
	}
	
	/**
	 * Creates a new {@code Color}.
	 * Parameters are given in the range of [0, 255].
	 * 
	 * @param r  a  red   value
	 * @param g  a  green value
	 * @param b  a  blue  value
	 * @param a  an alpha value
	 */
	public Color(int r, int g, int b, int a)
	{
		this(r / 255f, g / 255f, b / 255f, a / 255f);
	}
	
	/**
	 * Creates a new {@code Color}.
	 * Parameters are given in the range of [0.0, 1.0].
	 * 
	 * @param r  a  red   percentage
	 * @param g  a  green percentage
	 * @param b  a  blue  percentage
	 */
	public Color(float r, float g, float b)
	{
		this(r, g, b, 1f);
	}
	
	/**
	 * Creates a new {@code Color}.
	 * Parameters are given in the range of [0, 255].
	 * 
	 * @param r  a  red   value
	 * @param g  a  green value
	 * @param b  a  blue  value
	 */
	public Color(int r, int g, int b)
	{
		this(r, g, b, 255);
	}
			
	/**
	 * Creates a new {@code Color}.
	 * Hex codes are given as {@code 0xrrggbbaa}.
	 * 
	 * @param hex  a color hex code
	 */
	public Color(long hex)
	{
		this
		(
			(int)((hex >> 24) & 0xff),
			(int)((hex >> 16) & 0xff),
			(int)((hex >>  8) & 0xff),
			(int)((hex >>  0) & 0xff)
		);
	}
		

	/**
	 * Returns the {@code RGBA} values.
	 * Parameters are given in the
	 * range of [0.0, 1.0].
	 * 
	 * @return  rgba values
	 */
	public float[] RGBA()
	{
		return rgba;
	}
	
	/**
	 * Returns the {@code HSBA} values.
	 * Parameters are given in the
	 * range of [0.0, 1.0].
	 * 
	 * @return  hsba values
	 */
	public float[] HSBA()
	{
		float[] hsba = Array.copy.of(HSB(), 4);
		hsba[3] = rgba[3];
		return hsba;
	}
	
	/**
	 * Returns the {@code RGB} values.
	 * Parameters are given in the
	 * range of [0.0, 1.0].
	 * 
	 * @return  rgb values
	 */
	public float[] RGB()
	{
		return Array.copy.of(rgba, 3);
	}
	
	/**
	 * Returns the {@code HSB} values.
	 * Parameters are given in the
	 * range of [0.0, 1.0].
	 * 
	 * @return  hsb values
	 */
	public float[] HSB()
	{
		return java.awt.Color.RGBtoHSB
		(
			Integers.round(255 * rgba[0]),
			Integers.round(255 * rgba[1]),
			Integers.round(255 * rgba[2]),
			null
		);
	}
	
	/**
	 * Returns the {@code Red} value.
	 * 
	 * @return  a red value
	 */
	public float R()
	{
		return rgba[0];
	}
	
	/**
	 * Returns the {@code Green} value.
	 * 
	 * @return  a green value
	 */
	public float G()
	{
		return rgba[1];
	}
	
	/**
	 * Returns the {@code Blue} value.
	 * 
	 * @return  a blue value
	 */
	public float B()
	{
		return rgba[2];
	}
	
	/**
	 * Returns the {@code Alpha} value.
	 * 
	 * @return  an alpha value
	 */
	public float A()
	{
		return rgba[3];
	}
	
	
	/**
	 * Returns a {@code Color} hex code.
	 * Hex codes are given as {@code 0xrrggbbaa}.
	 * 
	 * @return  a hex code
	 */
	public long HexCode()
	{
		// The parentheses are necessary to maintain operation order.
		return ((Longs.round(255 * rgba[0]) & 0xff) << 24)
			 + ((Longs.round(255 * rgba[1]) & 0xff) << 16)
			 + ((Longs.round(255 * rgba[2]) & 0xff) <<  8)
			 + ((Longs.round(255 * rgba[3]) & 0xff) <<  0);
	}
	
	/**
	 * Returns a {@code Color} brightness.
	 * 
	 * @return  a brightness
	 */
	public float Brightness()
	{
		return Floats.max(rgba[0], rgba[1], rgba[2]);
	}
	
	
	/**
	 * Returns a {@code Token} formatter.
	 * 
	 * @param s  a format string
	 * @return   a formatter
	 * 
	 * 
	 * @see Format
	 */
	public Format<?> Formatter(String s)
	{
		return new ColorFormat(s);
	}
	
	@Override
	public Affine.Factory Factory()
	{
		return new Point(rgba).Factory();
	}
	
	@Override
	public Format<?> Formatter()
	{
		return Formatter(DEF_FORMAT);
	}
	
	@Override
	public float[] Array()
	{
		return RGBA();
	}
}