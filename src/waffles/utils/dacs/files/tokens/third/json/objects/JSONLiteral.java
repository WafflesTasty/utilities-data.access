package waffles.utils.dacs.files.tokens.third.json.objects;

import waffles.utils.dacs.files.tokens.literals.StringToken;
import waffles.utils.dacs.files.tokens.third.json.JSONObject;
import waffles.utils.dacs.utilities.parsers.primitive.LiteralParser;
import waffles.utils.dacs.utilities.parsers.tokens.StringTokenParser;
import waffles.utils.lang.Format;

/**
 * A {@code JSONLiteral} parses an object value through its {@code toString()} method.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 *
 *
 * @see StringToken
 * @see JSONObject
 */
public class JSONLiteral extends StringToken implements JSONObject
{
	private static final char DELIMITER = '"';
	
	
	/**
	 * A {@code JSONLiteral.Parser} parses a string to a {@code JSONLiteral}.
	 * As a subclass of {@code LiteralParser} it attempts the following
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
	 * @see LiteralParser
	 * @see JSONLiteral
	 */
	public static class Parser extends StringTokenParser<JSONLiteral>
	{
		/**
		 * Creates a new {@code JSONLiteral.Parser}.
		 */
		public Parser()
		{
			super(DELIMITER);
		}

		
		@Override
		public JSONLiteral generate(Object obj)
		{
			return new JSONLiteral(obj);
		}
	}
	
	
	/**
	 * Creates a new {@code JSONLiteral}.
	 * 
	 * @param val  an integer value
	 */
	public JSONLiteral(int val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code JSONLiteral}.
	 * 
	 * @param val  a float value
	 */
	public JSONLiteral(float val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code JSONLiteral}.
	 * 
	 * @param val  a boolean value
	 */
	public JSONLiteral(boolean val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code JSONLiteral}.
	 * 
	 * @param val  a string value
	 */
	public JSONLiteral(String val)
	{
		super(val);
	}
	
	/**
	 * Creates a new {@code StringToken}.
	 * 
	 * @param val  an object value
	 */
	public JSONLiteral(Object val)
	{
		super(val);
	}


	@Override
	public Format<JSONLiteral> Formatter(boolean isCompact)
	{
		return obj -> Formatter(DELIMITER).parse(obj);
	}
}