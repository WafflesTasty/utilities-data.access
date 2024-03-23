package waffles.utils.dacs.utilities.parsers.tokens;

import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.utilities.parsers.strings.StringParser;

/**
 * A {@code StringTokenParser} extends literal parsing with a general
 * string parser, which generates strings consisting of any
 * characters, enclosed by delimiter characters.
 *
 * @author Waffles
 * @since 21 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an object type
 * @see LiteralTokenParser
 */
public abstract class StringTokenParser<O> extends LiteralTokenParser<O>
{
	/**
	 * A {@code StringTokenParser.Base} defines an implementation
	 * for a {@code StringTokenParser} which returns basic tokens.
	 *
	 * @author Waffles
	 * @since 23 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see StringTokenParser
	 * @see StringToken
	 */
	public static class Base extends StringTokenParser<StringToken>
	{
		@Override
		public StringToken generate(Object obj)
		{
			return new StringToken(obj);
		}
	}
	
	/**
	 * Creates a new {@code StringTokenParser}.
	 * 
	 * @param d  a delimiter
	 */
	public StringTokenParser(char d)
	{
		add(new StringParser(d));
	}
	
	/**
	 * Creates a new {@code StringTokenParser}.
	 */
	public StringTokenParser()
	{
		this(StringToken.DELIMITER);
	}
}