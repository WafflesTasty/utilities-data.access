package waffles.utils.dacs.utilities.parsers.primitive;

import waffles.utils.dacs.utilities.parsers.choice.DemiChoiceParser;
import waffles.utils.dacs.utilities.parsers.strings.StringParser;

/**
 * A {@code LiteralParser} parses a string to an object literal.
 * As a subclass of {@code DemiChoiceParser} it attempts the following
 * primitive type parsers in succession.
 * <ul>
 * <li> {@code NullParser} </li>
 * <li> {@code BooleanParser} </li>
 * <li> {@code IntegerParser} </li>
 * <li> {@code FloatParser} </li>
 * <li> {@code StringParser} </li>
 * </ul>
 * 
 *
 * @author Waffles
 * @since 16 Mar 2024
 * @version 1.1
 *
 * 
 * @param <O>  an output type
 * @see DemiChoiceParser
 */
public abstract class LiteralParser<O> extends DemiChoiceParser<Object, O>
{
	/**
	 * Creates a new {@code LiteralParser}.
	 * 
	 * @param d  a string delimiter
	 */
	public LiteralParser(char d)
	{
		add(new NullParser<>());
		add(new BooleanParser());
		add(new IntegerParser());
		add(new FloatParser());
		add(new StringParser(d));
	}
}
