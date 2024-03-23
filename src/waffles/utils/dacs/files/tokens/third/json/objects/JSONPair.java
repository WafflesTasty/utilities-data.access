package waffles.utils.dacs.files.tokens.third.json.objects;

import waffles.utils.dacs.files.tokens.objects.TokenPair;
import waffles.utils.dacs.files.tokens.third.json.JSONObject;
import waffles.utils.dacs.utilities.parsers.objects.PairParser;
import waffles.utils.lang.Format;

/**
 * A {@code JSONPair} defines a single key-value pair in a {@code JSONMap}.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @see JSONLiteral
 * @see JSONObject
 * @see TokenPair
 */
public class JSONPair implements TokenPair<JSONLiteral, JSONObject>
{
	/**
	 * Defines a separator for a {@code JSONPair}.
	 */
	public static final char SEPARATOR = ':';
	
	/**
	 * A {@code JSONPair.Parser} parses a key-value pair from a string.
	 *
	 * @author Waffles
	 * @since 19 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see PairParser
	 * @see JSONPair
	 */
	public static class Parser extends PairParser<JSONPair>
	{
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(SEPARATOR);
		}

						
		@Override
		public JSONPair generate(Object k, Object v)
		{
			JSONLiteral key = (JSONLiteral) k;
			JSONObject val = (JSONObject) v;
			return new JSONPair(key, val);
		}
		
		@Override
		public JSONObject.Parser ValueParser()
		{
			return new JSONObject.Parser();
		}
		
		@Override
		public JSONLiteral.Parser KeyParser()
		{
			return new JSONLiteral.Parser();
		}
	}

	
	private JSONObject val;
	private JSONLiteral key;
	
	/**
	 * Creates a new {@code JSONPair}.
	 * 
	 * @param k  a key literal
	 * @param v  a value object
	 * 
	 * 
	 * @see JSONLiteral
	 * @see JSONObject
	 */
	public JSONPair(JSONLiteral k, JSONObject v)
	{
		key = k; val = v;
	}


	@Override
	public Format<TokenPair<JSONLiteral, JSONObject>> Formatter()
	{
		return Formatter(SEPARATOR);
	}


	
	
	@Override
	public JSONObject Value()
	{
		return val;
	}
	


	@Override
	public JSONLiteral Key()
	{
		return key;
	}
}