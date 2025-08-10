package waffles.utils.dacs.files.tokens.parsers.primitive.choice;

import waffles.utils.dacs.files.tokens.parsers.ChoiceParser;
import waffles.utils.dacs.files.tokens.parsers.primitive.BooleanParser;
import waffles.utils.dacs.files.tokens.parsers.primitive.FloatParser;
import waffles.utils.dacs.files.tokens.parsers.primitive.IntegerParser;
import waffles.utils.dacs.files.tokens.parsers.primitive.NullParser;

/**
 * A {@code PrimitiveParser} parses a string to a primitive object.
 * As a subclass of {@code ChoiceParser} it attempts the following
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
 * @see ChoiceParser
 */
public class PrimitiveParser<O> extends ChoiceParser<Object, O>
{
	/**
	 * Creates a new {@code PrimitiveParser}.
	 */
	public PrimitiveParser()
	{
		add(new NullParser<>());
		add(new BooleanParser());
		add(new IntegerParser());
		add(new FloatParser());
	}
}