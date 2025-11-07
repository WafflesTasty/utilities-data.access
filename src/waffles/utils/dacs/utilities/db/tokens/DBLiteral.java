package waffles.utils.dacs.utilities.db.tokens;

import waffles.utils.lang.tokens.format.Format;
import waffles.utils.tools.patterns.properties.Gateway;

/**
 * A {@code DBLiteral} defines a {@code StringToken} with literal SQL formatting.
 *
 * @author Waffles
 * @since 04 Nov 2025
 * @version 1.1
 *
 * 
 * @see DBToken
 */
public class DBLiteral extends DBToken
{
	/**
	 * The {@code Hints} class defines format settings for a {@code DBLiteral}.
	 *
	 * @author Waffles
	 * @since 04 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @see DBToken
	 */
	public static class Hints extends DBToken.Hints
	{
		@Override
		public Gateway<String> Delimit()
		{
			return s -> false;
		};
	}
	
	
	/**
	 * Creates a new {@code DBLiteral}.
	 * 
	 * @param val  an integer value
	 */
	public DBLiteral(int val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code DBLiteral}.
	 * 
	 * @param val  a float value
	 */
	public DBLiteral(float val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code DBLiteral}.
	 * 
	 * @param val  a boolean value
	 */
	public DBLiteral(boolean val)
	{
		super(val);
	}
	
	
	/**
	 * Creates a new {@code DBLiteral}.
	 * 
	 * @param val  a string value
	 */
	public DBLiteral(String val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code DBLiteral}.
	 * 
	 * @param val  an object value
	 */
	public DBLiteral(Object val)
	{
		super(val);
	}
	
	
	@Override
	public Format<?> Formatter()
	{
		return Formatter(new Hints());
	}
}