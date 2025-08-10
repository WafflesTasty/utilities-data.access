package waffles.utils.dacs.files.tokens.literals;

import waffles.utils.dacs.files.tokens.parsers.primitive.choice.LiteralParser;

/**
 * A {@code LiteralToken} extends the {@code PrimitiveToken} token with a literal string.
 * This string can consist of any number of non-whitespace characters.
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
	 * A {@code LiteralToken.Parser} generates literal tokens.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see LiteralParser
	 */
	public static class Parser extends LiteralParser<LiteralToken>
	{
		@Override
		public LiteralToken compute(Object o)
		{
			return new LiteralToken(o);
		}
	}
	
	
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