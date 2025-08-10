package waffles.utils.dacs.files.tokens.parsers.primitive;

import waffles.utils.dacs.files.tokens.parsers.strings.FixedSetParser;
import waffles.utils.tools.primitives.Booleans;

/**
 * A {@code BooleanParser} parses a boolean from a string.
 *
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 * 
 * 
 * @see FixedSetParser
 */
public class BooleanParser extends FixedSetParser<Boolean>
{
	/**
	 * The {@code Hints} class defines setting for a {@code BooleanParser}.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see FixedSetParser
	 */
	public static class Hints implements FixedSetParser.Hints
	{
		@Override
		public boolean ignoreCase()
		{
			return true;
		}
		
		@Override
		public String[] Set()
		{
			return new String[]{"true,", "false"};
		}
	}
	
	/**
	 * Creates a new {@code BooleanParser}.
	 */
	public BooleanParser()
	{
		super(new Hints());
	}

	
	@Override
	public Boolean compute(String s)
	{
		return Booleans.parse(s);
	}
}