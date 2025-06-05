package waffles.utils.dacs.files.tokens.literals;

import waffles.utils.dacs.files.tokens.Token;
import waffles.utils.lang.Format;

/**
 * A {@code PrimitiveToken} defines a {@code Token} for a primitive value.
 * Primitives are parsed based on its {@code toString()} method.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 * 
 * 
 * @see Token
 */
public class PrimitiveToken implements Token
{
	private Object value;
	
	/**
	 * Creates a new {@code PrimitiveToken}.
	 * 
	 * @param val  an integer value
	 */
	public PrimitiveToken(int val)
	{
		this((Integer) val);
	}
	
	/**
	 * Creates a new {@code PrimitiveToken}.
	 * 
	 * @param val  a float value
	 */
	public PrimitiveToken(float val)
	{
		this((Float) val);
	}
	
	/**
	 * Creates a new {@code PrimitiveToken}.
	 * 
	 * @param val  a boolean value
	 */
	public PrimitiveToken(boolean val)
	{
		this((Boolean) val);
	}
	
	/**
	 * Creates a new {@code PrimitiveToken}.
	 * 
	 * @param val  an object value
	 */
	public PrimitiveToken(Object val)
	{
		value = val;
	}
	
	
	/**
	 * Changes the value of the {@code PrimitiveToken}.
	 * 
	 * @param val  an object value
	 */
	public void setValue(Object val)
	{
		value = val;
	}
	
	/**
	 * Returns the value of the {@code PrimitiveToken}.
	 * 
	 * @return  an object value
	 */
	public <O> O Value()
	{
		return (O) value;
	}


	@Override
	public Format<PrimitiveToken> Formatter()
	{
		return obj ->
		{
			Object val = obj.Value();
			if(val != null)
			{
				return val.toString();
			}
			
			return "";
		};
	}
}