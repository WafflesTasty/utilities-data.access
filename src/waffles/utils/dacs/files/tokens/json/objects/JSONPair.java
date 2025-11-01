package waffles.utils.dacs.files.tokens.json.objects;

import waffles.utils.dacs.files.tokens.json.JSONObject;
import waffles.utils.lang.tokens.PairToken;
import waffles.utils.lang.tokens.format.Format;
import waffles.utils.lang.tokens.parsers.basic.PairParser;

/**
 * A {@code JSONPair} defines a single key-value pair for a {@code JSONMap}.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @see JSONLiteral
 * @see JSONObject
 * @see PairToken
 */
public class JSONPair implements PairToken<JSONLiteral, JSONObject>
{
	/**
	 * Defines a default separator for a {@code JSONPair}.
	 */
	public static final char SEPARATOR = ':';
	
	/**
	 * A {@code JSONPair.Parser} parses a {@code JSONPair}.
	 * These values can be any {@code JSONLiteral} key and
	 * {@code JSONObject} value separated by a colon.
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
		private JSONLiteral.Parser key;
		private JSONObject.Parser  val;
		
		/**
		 * Creates a new {@code Parser}.
		 */
		public Parser()
		{
			super(SEPARATOR);
		}

						
		@Override
		public JSONPair generate()
		{
			JSONLiteral k = key.generate();
			JSONObject  v = val.generate();
			
			return new JSONPair(k, v);
		}

		@Override
		public JSONLiteral.Parser KeyParser()
		{
			if(key == null)
			{
				key = new JSONLiteral.Parser();
			}
			
			return key;
		}
		
		@Override
		public JSONObject.Parser ValueParser()
		{
			if(val == null)
			{
				val = new JSONObject.Parser();
			}
			
			return val;
		}
	}

	
	private JSONLiteral key;
	private JSONObject  val;
	
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
	public JSONLiteral Key()
	{
		return key;
	}

	@Override
	public Format<?> Formatter()
	{
		return Formatter(SEPARATOR);
	}

	@Override
	public JSONObject Value()
	{
		return val;
	}
}