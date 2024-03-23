package waffles.utils.dacs.files.tokens.third.json;

import waffles.utils.dacs.files.tokens.Token;
import waffles.utils.dacs.files.tokens.third.json.objects.JSONList;
import waffles.utils.dacs.files.tokens.third.json.objects.JSONLiteral;
import waffles.utils.dacs.files.tokens.third.json.objects.JSONMap;
import waffles.utils.dacs.utilities.parsers.choice.ChoiceParser;
import waffles.utils.lang.Format;

/**
 * A {@code JSONObject} can be parsed by a {@code JSONReader} or {@code JSONWriter}.
 *
 * @author Waffles
 * @since 27 Feb 2024
 * @version 1.1
 * 
 * 
 * @see Token
 */
public interface JSONObject extends Token
{
	/**
	 * A {@code JSONObject.Parser} parses strings into a {@code JSONObject}.
	 * As a subclass of {@code ChoiceParser} it chooses between the three
	 * subtypes: {@code JSONMap}, {@code JSONList}, {@code JSONLiteral}.
	 *
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see ChoiceParser
	 * @see JSONObject
	 */
	public static class Parser extends ChoiceParser<JSONObject>
	{
		/**
		 * Creates a new {@code JSONObject.Parser}.
		 */
		public Parser()
		{
			add(new JSONMap.Parser());
			add(new JSONList.Parser());
			add(new JSONLiteral.Parser());
		}
	}
	
	
	/**
	 * Returns a formatter for the {@code JSONObject}.
	 * 
	 * @param isCompact  single line or multi-line
	 * @return  a json formatter
	 * 
	 * 
	 * @see Format
	 */
	public abstract Format<?> Formatter(boolean isCompact);
	
	/**
	 * Parses the {@code JSONObject} into a string.
	 * 
	 * @param isCompact  single line or multi-line
	 * @return  a json string
	 */
	public default String parse(boolean isCompact)
	{
		return Formatter(isCompact).castAndParse(this);
	}
	
	
	@Override
	public default Format<?> Formatter()
	{
		return Formatter(true);
	}
}