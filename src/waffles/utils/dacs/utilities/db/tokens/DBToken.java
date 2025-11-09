package waffles.utils.dacs.utilities.db.tokens;

import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.format.values.StringFormat;
import waffles.utils.lang.tokens.values.primitive.StringToken;
import waffles.utils.tools.patterns.properties.Gateway;

/**
 * A {@code DBToken} defines a {@code StringToken} with SQL formatting.
 *
 * @author Waffles
 * @since 04 Nov 2025
 * @version 1.1
 *
 * 
 * @see StringToken
 */
public class DBToken extends StringToken
{
	/**
	 * The {@code Hints} class defines format settings for a {@code DBToken}.
	 *
	 * @author Waffles
	 * @since 04 Nov 2025
	 * @version 1.1
	 *
	 *
	 * @see StringFormat
	 */
	public static class Hints implements StringFormat.Hints
	{
		@Override
		public char Delimiter()
		{
			return '\'';
		}
		
		@Override
		public Gateway<String> Delimit()
		{
			return s -> true;
		};

		@Override
		public String Null()
		{
			return "NULL";
		}
	}
	
	
	/**
	 * Creates a new {@code DBToken}.
	 * 
	 * @param val  an integer value
	 */
	public DBToken(int val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code DBToken}.
	 * 
	 * @param val  a float value
	 */
	public DBToken(float val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code DBToken}.
	 * 
	 * @param val  a boolean value
	 */
	public DBToken(boolean val)
	{
		super(val);
	}
	
	
	/**
	 * Creates a new {@code DBToken}.
	 * 
	 * @param val  a string value
	 */
	public DBToken(String val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code DBToken}.
	 * 
	 * @param val  an object value
	 */
	public DBToken(Object val)
	{
		super(val);
	}
	
		
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof DBToken)
		{
			DBToken t = (DBToken) o;
			
			String s1 =   condense();
			String s2 = t.condense();
			
			return s1.equals(s2);
		}
		
		return false;
	}
	
	@Override
	public Format<?> Formatter()
	{
		return Formatter(new Hints());
	}
	
	@Override
	public int hashCode()
	{
		return condense().hashCode();
	}
}