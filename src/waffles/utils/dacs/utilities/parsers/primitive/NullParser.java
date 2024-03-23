package waffles.utils.dacs.utilities.parsers.primitive;

import waffles.utils.dacs.utilities.parsers.strings.FixedSetParser;

/**
 * A {@code NullParser} parses a null value from a string.
 *
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 * 
 * 
 * @param <O>  an output token class
 * @see FixedSetParser
 */
public class NullParser<O> extends FixedSetParser<O>
{
	/**
	 * Creates a new {@code BooleanParser}.
	 */
	public NullParser()
	{
		super("null");
	}


	@Override
	public O generate(String s)
	{
		return null;
	}
}