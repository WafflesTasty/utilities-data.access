package waffles.utils.dacs.files.tokens.third.json.objects;

import waffles.utils.dacs.files.tokens.Token;
import waffles.utils.dacs.files.tokens.third.json.JSONObject;
import waffles.utils.dacs.utilities.formats.ListFormat;
import waffles.utils.dacs.utilities.formats.ListFormattable;
import waffles.utils.dacs.utilities.parsers.objects.ListParser;
import waffles.utils.sets.indexed.delegate.List;

/**
 * A {@code JSONList} defines a formattable list of {@code JSONObjects}.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @see ListFormattable
 * @see JSONObject
 * @see List
 */
public class JSONList extends List<JSONObject> implements JSONObject, ListFormattable<JSONObject>
{
	/**
	 * A {@code JSONList.Parser} parses a string to a {@code JSONList}.
	 * As a subclass of {@code ListParser} it looks for the appropriate
	 * delimiters '[ , ]' and parses arbitrary {@code JSONObjects}
	 * in between.
	 *
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see JSONList
	 * @see Token
	 */
	public static class Parser implements Token.Parser<JSONList>
	{
		private ListParser<JSONObject> list;		
		
		/**
		 * Creates a new {@code JSONList.Parser}.
		 */
		public Parser()
		{
			list = new ListParser<>
			(
				'[', ',', ']',
				() -> new JSONObject.Parser()
			);
		}

		
		@Override
		public boolean consume(Character s)
		{
			return list.consume(s);
		}
		
		@Override
		public JSONList generate()
		{
			List<JSONObject> src = list.generate();
			JSONList tgt = new JSONList();
			for(JSONObject obj : src)
			{
				tgt.add(obj);
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
	public Iterable<JSONObject> FormatList()
	{
		return this;
	}
	
	@Override
	public ListFormat<JSONObject> Formatter(boolean isCompact)
	{
		return new ListFormat<>(isCompact, '[', ',', ']');
	}

	@Override
	public ListFormat<JSONObject> Formatter()
	{
		return Formatter(true);
	}
}