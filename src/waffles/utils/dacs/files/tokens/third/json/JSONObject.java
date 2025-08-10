package waffles.utils.dacs.files.tokens.third.json;

import waffles.utils.dacs.files.tokens.parsers.ChoiceParser;
import waffles.utils.dacs.files.tokens.third.json.objects.JSONList;
import waffles.utils.dacs.files.tokens.third.json.objects.JSONLiteral;
import waffles.utils.dacs.files.tokens.third.json.objects.JSONMap;
import waffles.utils.lang.tokens.Token;

/**
 * A {@code JSONObject} defines a generic element in a {@code JSONFile}.
 * It can be parsed by a {@code JSONReader} or {@code JSONWriter}.
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
	 * A {@code JSONObject.Parser} parses a {@code JSONObject}.
	 * As a subclass of {@code ChoiceParser} it chooses between three
	 * possible subtypes: {@code JSONMap}, {@code JSONList}
	 * and {@code JSONLiteral}.
	 *
	 * @author Waffles
	 * @since 16 Mar 2024
	 * @version 1.1
	 *
	 * 
	 * @see ChoiceParser
	 * @see JSONObject
	 */
	public static class Parser extends ChoiceParser<JSONObject, JSONObject>
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
}