package waffles.utils.dacs.files.tokens.json.objects;

import waffles.utils.dacs.files.tokens.json.JSONObject;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.parsers.choice.primitive.StringParser;
import waffles.utils.lang.tokens.primitive.StringToken;
import waffles.utils.tools.patterns.properties.values.Valuable;

/**
 * A {@code JSONLiteral} defines a primitive value in a {@code JSONFile}.
 * It behaves precisely like a {@code StringToken} in terms of
 * formatting and parsing methods.
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
	/**
	 * Defines the default delimiter for a {@code JSONLiteral}.
	 */
	public static final char DELIMITER = '"';
	
	/**
	 * A {@code JSONLiteral.Parser} parses a s{@code JSONLiteral}.
	 * As a subclass of {@code StringParser} it attempts the
	 * following primitive type parsers in succession.
	 * <ul>
	 * <li> {@code NullParser} </li>
	 * <li> {@code BooleanParser} </li>
	 * <li> {@code IntegerParser} </li>
	 * <li> {@code FloatParser} </li>
	 * <li> {@code StringParser} </li>
	 * </ul>
	 * 
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see StringParser
	 * @see JSONLiteral
	 */
	public static class Parser extends StringParser<JSONLiteral>
	{
		/**
		 * Creates a new {@code JSONLiteral.Parser}.
		 */
		public Parser()
		{
			super(DELIMITER);
		}

		
		@Override
		public JSONLiteral compute(Object o)
		{
			return new JSONLiteral(o);
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
	 * Creates a new {@code JSONLiteral}.
	 * 
	 * @param val  an object value
	 */
	public JSONLiteral(Object val)
	{
		super(val);
	}


	@Override
	public Format<? extends Valuable<?>> Formatter()
	{
		return Formatter(DELIMITER);
	}
}