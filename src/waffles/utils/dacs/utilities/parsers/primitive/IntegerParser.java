package waffles.utils.dacs.utilities.parsers.primitive;

import waffles.utils.dacs.utilities.parsers.BasicParser;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code IntegerParser} parses a signed integer from a string.
 *
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 *
 * 
 * @see BasicParser
 */
public class IntegerParser extends BasicParser<Integer>
{
	private static final char[] SIGNS = new char[]{'-', '+'};
	
	
	@Override
	public Integer generate(String s)
	{
		return Integers.parse(s);
	}

	@Override
	public boolean allows(Character s)
	{
		if(Length() == 0)
		{
			if(Array.contents.has(SIGNS, s))
			{
				return true;
			}
		}
		
		return Character.isDigit(s);
	}
}