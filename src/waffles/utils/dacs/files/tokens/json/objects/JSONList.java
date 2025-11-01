package waffles.utils.dacs.files.tokens.json.objects;

import waffles.utils.dacs.files.tokens.json.JSONObject;
import waffles.utils.lang.tokens.ListToken;
import waffles.utils.lang.tokens.format.ListFormat;
import waffles.utils.lang.tokens.parsers.Parsable;
import waffles.utils.lang.tokens.parsers.cyclic.ListParser;
import waffles.utils.sets.countable.wrapper.JavaList;

/**
 * A {@code JSONList} defines a {@code JSONObject} list.
 * A list is enclosed by between two brackets, i.e. [v1, v2]
 * with its values separated by a comma.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 * 
 * 
 * @see JSONObject
 * @see ListToken
 * @see JavaList
 */
public class JSONList extends JavaList<JSONObject> implements JSONObject, ListToken<JSONObject>
{
	/**
	 * Defines a default lower bound for a {@code JSONList}.
	 */
	public static final char LOWER_BOUND = '[';
	/**
	 * Defines a default upper bound for a {@code JSONList}.
	 */
	public static final char UPPER_BOUND = ']';
	/**
	 * Defines a default separator for a {@code JSONList}.
	 */
	public static final char SEPARATOR = ',';
	
	
	/**
	 * The {@code FormatHints} define format hints for a {@code JSONList}.
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
	 * The {@code ParserHints} define parser hints for a {@code JSONList}.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 * 
	 * @see ListParser
	 * @see JSONObject
	 */
	public static class ParserHints implements ListParser.Hints<JSONObject>
	{
		@Override
		public JSONObject.Parser Parser()
		{
			return new JSONObject.Parser();
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
	 * A {@code JSONList.Parser} parses a{@code JSONList}.
	 * As a subclass of {@code ListParser} it looks for the
	 * appropriate delimiters '[ , ]' and parses any
	 * {@code JSONObject} it finds in between.
	 *
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 * 
	 * 
	 * @see JSONList
	 * @see Parsable
	 */
	public static class Parser implements Parsable<JSONList>
	{
		private ListParser<JSONObject> list;		
		
		/**
		 * Creates a new {@code JSONList.Parser}.
		 */
		public Parser()
		{
			list = new ListParser<>(new ParserHints());
		}

		
		@Override
		public JSONList generate()
		{
			JavaList<JSONObject> src = list.generate();
			JSONList tgt = new JSONList();
			for(JSONObject obj : src)
			{
				tgt.add(obj);
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
	public ListFormat<JSONObject> Formatter()
	{
		return new ListFormat<>(new FormatHints());
	}

	@Override
	public Iterable<JSONObject> Tokens()
	{
		return this;
	}
}