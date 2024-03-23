package waffles.utils.dacs.utilities.parsers.strings;

import waffles.utils.dacs.utilities.parsers.choice.DemiChoiceParser;

/**
 * A {@code FixedSetParser} parses one of a set of fixed string literals.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an output type
 * @see DemiChoiceParser
 */
public abstract class FixedSetParser<O> extends DemiChoiceParser<String, O>
{
	/**
	 * Creates a new {@code FixedSetParser}.
	 * 
	 * @param set  a set of string literals
	 */
	public FixedSetParser(String... set)
	{
		for(String s : set)
		{
			add(new FixedParser(s));
		}
	}
}