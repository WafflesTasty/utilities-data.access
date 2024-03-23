package waffles.utils.dacs.files.tokens.third.json;

import waffles.utils.dacs.files.tokens.TokenWriter;

/**
 * A {@code JSONWriter} writes {@code JSONObjects} to a {@code File}.
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
	private boolean isCompact;
	
	/**
	 * Creates a new {@code JSONWriter}.
	 * 
	 * @param compact  single-line or multi-line parsing
	 */
	public JSONWriter(boolean compact)
	{
		isCompact = compact;
	}

	@Override
	public String onParse(JSONObject obj)
	{
		return obj.parse(isCompact);
	}
}