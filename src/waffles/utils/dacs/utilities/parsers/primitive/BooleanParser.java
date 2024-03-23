package waffles.utils.dacs.utilities.parsers.primitive;

import waffles.utils.dacs.utilities.parsers.strings.FixedSetParser;
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
	 * Creates a new {@code BooleanParser}.
	 */
	public BooleanParser()
	{
		super("true", "false");
	}

	
	@Override
	public Boolean generate(String s)
	{
		return Booleans.parse(s);
	}
}