package waffles.utils.dacs.files.tokens.json.objects;

import waffles.utils.dacs.files.tokens.json.JSONObject;
import waffles.utils.lang.tokens.MapToken;
import waffles.utils.lang.tokens.format.ListFormat;
import waffles.utils.lang.tokens.parsers.Parsable;
import waffles.utils.lang.tokens.parsers.cyclic.ListParser;
import waffles.utils.sets.countable.keymaps.wrapper.JavaMap;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code JSONMap} defines a mappings with a {@code JSONLiteral} key
 * and a {@code JSONObject} value. This mapping is enclosed by parentheses,
 * i.e. {k1:v1, k2:v2} with each pair separated by a colon.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @see MapToken
 * @see JSONLiteral
 * @see JSONObject
 * @see JavaMap
 */
public class JSONMap extends JavaMap<JSONLiteral, JSONObject> implements JSONObject, MapToken<JSONPair>
{
	/**
	 * Defines a default lower bound for a {@code JSONMap}.
	 */
	public static final char LOWER_BOUND = '{';
	/**
	 * Defines a default upper bound for a {@code JSONMap}.
	 */
	public static final char UPPER_BOUND = '}';
	/**
	 * Defines a default separator for a {@code JSONMap}.
	 */
	public static final char SEPARATOR = ',';
	
	
	/**
	 * The {@code FormatHints} define format hints for a {@code JSONMap}.
	 *
	 * @author Waffles
	 * @since 10 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see ListFormat
	 */
	public static class FormatHints implements ListFormat.Hints
	{
		@Override
		public Character LowerBound()
		{
			return LOWER_BOUND;
		}

		@Override
		public Character UpperBound()
		{
			return UPPER_BOUND;
		}
		
		@Override
		public char Separator()
		{
			return SEPARATOR;
		}
	}
		
	/**
	 * The {@code ParserHints} define parser hints for a {@code JSONMap}.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see ListParser
	 * @see JSONPair
	 */
	public static class ParserHints implements ListParser.Hints<JSONPair>
	{
		@Override
		public JSONPair.Parser Parser()
		{
			return new JSONPair.Parser();
		}
		
		
		@Override
		public char LowerBound()
		{
			return LOWER_BOUND;
		}
		
		@Override
		public char UpperBound()
		{
			return UPPER_BOUND;
		}
		
		@Override
		public char Separator()
		{
			return SEPARATOR;
		}
	}

	/**
	 * A {@code JSONMap.Parser} parses a {@code JSONMap}.
	 * As a subclass of {@code ListParser} it looks for the
	 * appropriate delimiters '{ , }' and parses any
	 * {@code JSONPair} it finds in between.
	 *
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see Parsable
	 * @see JSONMap
	 */
	public static class Parser implements Parsable<JSONMap>
	{
		private ListParser<JSONPair> list;
		
		/**
		 * Creates a new {@code JSONMap.Parser}.
		 */
		public Parser()
		{
			list = new ListParser<>(new ParserHints());
		}

		
		@Override
		public JSONMap generate()
		{
			JavaList<JSONPair> src = list.generate();
			JSONMap tgt = new JSONMap();
			for(JSONPair p : src)
			{
				tgt.put(p.Key(), p.Value());
			}
			
			return tgt;
		}
		
		@Override
		public boolean consume(Character s)
		{
			return list.consume(s);
		}
		
		@Override
		public void reset()
		{
			list.reset();
		}
	}
	
	
	@Override
	public ListFormat<JSONPair> Formatter()
	{
		return new ListFormat<>(new FormatHints());
	}
			
	@Override
	public JSONPair create(JSONLiteral k, JSONObject v)
	{
		return new JSONPair(k, v);
	}
		
	@Override
	public Iterable<JSONPair> Tokens()
	{
		return Pairs();
	}
}