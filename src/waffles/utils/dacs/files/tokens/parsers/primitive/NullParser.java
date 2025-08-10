package waffles.utils.dacs.files.tokens.parsers.primitive;

import waffles.utils.dacs.files.tokens.parsers.strings.FixedSetParser;

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
	 * Creates a new {@code NullParser}.
	 */
	public NullParser()
	{
		super("null", "NULL");
	}


	@Override
	public O compute(String s)
	{
		return null;
	}
}