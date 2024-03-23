package waffles.utils.dacs.utilities.parsers.tokens;

import waffles.utils.dacs.utilities.parsers.choice.DemiChoiceParser;
import waffles.utils.dacs.utilities.parsers.primitive.BooleanParser;
import waffles.utils.dacs.utilities.parsers.primitive.FloatParser;
import waffles.utils.dacs.utilities.parsers.primitive.IntegerParser;
import waffles.utils.dacs.utilities.parsers.primitive.NullParser;

/**
 * A {@code PrimitiveTokenParser} parses a string to a primitive token.
 * As a subclass of {@code DemiChoiceParser} it attempts the following
 * primitive type parsers in succession.
 * <ul>
 * <li> {@code NullParser} </li>
 * <li> {@code BooleanParser} </li>
 * <li> {@code IntegerParser} </li>
 * <li> {@code FloatParser} </li>
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
public abstract class PrimitiveTokenParser<O> extends DemiChoiceParser<Object, O>
{
	/**
	 * Creates a new {@code PrimitiveTokenParser}.
	 */
	public PrimitiveTokenParser()
	{
		add(new NullParser<>());
		add(new BooleanParser());
		add(new IntegerParser());
		add(new FloatParser());
	}
}