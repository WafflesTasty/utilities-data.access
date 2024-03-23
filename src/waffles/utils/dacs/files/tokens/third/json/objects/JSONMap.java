package waffles.utils.dacs.files.tokens.third.json.objects;

import waffles.utils.dacs.files.tokens.Token;
import waffles.utils.dacs.files.tokens.third.json.JSONObject;
import waffles.utils.dacs.utilities.formats.ListFormat;
import waffles.utils.dacs.utilities.formats.MapFormattable;
import waffles.utils.dacs.utilities.parsers.objects.ListParser;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.sets.keymaps.delegate.JHashMap;

/**
 * A {@code JSONMap} defines a formattable key-value map between {@code JSONLiterals} and {@code JSONObjects}.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @see MapFormattable
 * @see JSONLiteral
 * @see JSONObject
 * @see JHashMap
 */
public class JSONMap extends JHashMap<JSONLiteral, JSONObject> implements JSONObject, MapFormattable<JSONPair>
{
	/**
	 * A {@code JSONMap.Parser} parses a string to a {@code JSONMap}.
	 * As a subclass of {@code ListParser} it looks for the appropriate
	 * delimiters '{ , }' and parses arbitrary {@code JSONValues}
	 * in between.
	 *
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see ListParser
	 * @see JSONPair
	 */
	public static class Parser implements Token.Parser<JSONMap>
	{
		private ListParser<JSONPair> list;
		
		/**
		 * Creates a new {@code JSONMap.Parser}.
		 */
		public Parser()
		{
			list = new ListParser<>
			(
				'{', ',', '}',
				() -> new JSONPair.Parser()
			);
		}

		
		@Override
		public boolean consume(Character s)
		{
			return list.consume(s);
		}
		
		@Override
		public JSONMap generate()
		{
			List<JSONPair> src = list.generate();
			JSONMap tgt = new JSONMap();
			for(JSONPair p : src)
			{
				tgt.put(p.Key(), p.Value());
			}
			
			return tgt;
		}

		@Override
		public void reset()
		{
			list.reset();
		}
	}

	
	@Override
	public Iterable<JSONPair> FormatList()
	{
		return Pairs();
	}
		
	@Override
	public JSONPair createPair(JSONLiteral key, JSONObject val)
	{
		return new JSONPair(key, val);
	}
	
	@Override
	public ListFormat<JSONPair> Formatter(boolean isCompact)
	{
		return new ListFormat<>(isCompact, '{', ',', '}');
	}
	
	@Override
	public ListFormat<JSONPair> Formatter()
	{
		return Formatter(true);
	}
}