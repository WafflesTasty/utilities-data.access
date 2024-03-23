package waffles.utils.dacs.utilities.parsers.tokens;

import waffles.utils.dacs.files.tokens.literals.LiteralToken;
import waffles.utils.dacs.utilities.parsers.strings.AnyParser;

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
 * @see PrimitiveTokenParser
 */
public abstract class LiteralTokenParser<O> extends PrimitiveTokenParser<O>
{
	/**
	 * A {@code LiteralTokenParser.Base} defines an implementation
	 * for a {@code LiteralTokenParser} which returns basic tokens.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see LiteralTokenParser
	 * @see LiteralToken
	 */
	public static class Base extends StringTokenParser<LiteralToken>
	{
		@Override
		public LiteralToken generate(Object obj)
		{
			return new LiteralToken(obj);
		}
	}
	
	
	/**
	 * Creates a new {@code LiteralTokenParser}.
	 */
	public LiteralTokenParser()
	{
		// A literal token halts on whitespace...
		add(new AnyParser(s -> !Character.isWhitespace(s))
		{
			@Override
			public boolean consume(Character s)
			{
				// ...but at the start,
				if(Length() == 0)
				{
					// it consumes whitespace.
					if(Character.isWhitespace(s))
					{
						return true;
					}
				}

				return super.consume(s);
			}
		});
	}
}