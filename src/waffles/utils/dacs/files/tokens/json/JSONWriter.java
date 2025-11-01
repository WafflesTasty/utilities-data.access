package waffles.utils.dacs.files.tokens.json;

import waffles.utils.dacs.files.tokens.TokenWriter;

/**
 * A {@code JSONWriter} writes a {@code JSONObject} to a {@code File}.
 *
 * @author Waffles
 * @since 15 Mar 2024
 * @version 1.1
 *
 * 
 * @see TokenWriter
 * @see JSONObject
 */
public class JSONWriter extends TokenWriter<JSONObject>
{
	/**
	 * Creates a new {@code JSONWriter}.
	 * 
	 * @param cnd  a condense state
	 */
	public JSONWriter(boolean cnd)
	{
		super(cnd);
	}
}