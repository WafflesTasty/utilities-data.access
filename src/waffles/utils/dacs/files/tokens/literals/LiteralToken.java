package waffles.utils.dacs.files.tokens.literals;

/**
 * A {@code LiteralToken} extends the {@code PrimitiveToken} token with a literal string parser.
 * This parser allows the {@code LiteralToken} to define a literal string value,
 * which consists of any number of non-whitespace characters.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 * 
 * @see PrimitiveToken
 */
public class LiteralToken extends PrimitiveToken
{
	/**
	 * Creates a new {@code LiteralToken}.
	 * 
	 * @param val  an integer value
	 */
	public LiteralToken(int val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code LiteralToken}.
	 * 
	 * @param val  a float value
	 */
	public LiteralToken(float val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code LiteralToken}.
	 * 
	 * @param val  a boolean value
	 */
	public LiteralToken(boolean val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code LiteralToken}.
	 * 
	 * @param val  a string value
	 */
	public LiteralToken(String val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code LiteralToken}.
	 * 
	 * @param val  an object value
	 */
	public LiteralToken(Object val)
	{
		super(val);
	}
}