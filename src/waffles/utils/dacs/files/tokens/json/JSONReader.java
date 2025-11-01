package waffles.utils.dacs.files.tokens.json;

import waffles.utils.dacs.files.tokens.TokenReader;

/**
 * A {@code JSONReader} reads {@code JSONObjects} from a {@code File}.
 *
 * @author Waffles
 * @since 16 Mar 2024
 * @version 1.1
 * 
 * 
 * @see TokenReader
 * @see JSONObject
 */
public class JSONReader extends TokenReader<JSONObject>
{
	/**
	 * Creates a new {@code JSONReader}.
	 */
	public JSONReader()
	{
		super(new JSONObject.Parser());
	}
}