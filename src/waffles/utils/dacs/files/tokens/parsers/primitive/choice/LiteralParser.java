package waffles.utils.dacs.files.tokens.parsers.primitive.choice;

import waffles.utils.lang.Characters;
import waffles.utils.lang.tokens.parsers.basic.AnyParser;

/**
 * A {@code LiteralTokenParser} extends primitive parsing with a literal
 * string parser, which generates strings consisting of
 * any number of non-whitespace characters.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see PrimitiveParser
 */
public class LiteralParser<O> extends PrimitiveParser<O>
{
	/**
	 * Creates a new {@code LiteralParser}.
	 */
	public LiteralParser()
	{
		// A literal token halts on whitespace...
		add(new AnyParser(s -> !Characters.isWhiteSpace(s))
		{
			@Override
			public boolean consume(Character s)
			{
				// ...but at the start,
				if(Length() == 0)
				{
					// it consumes whitespace.
					if(Characters.isWhiteSpace(s))
					{
						return true;
					}
				}

				return super.consume(s);
			}
		});
	}
}