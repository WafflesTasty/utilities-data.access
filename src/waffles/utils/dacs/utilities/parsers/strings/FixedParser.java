package waffles.utils.dacs.utilities.parsers.strings;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.dacs.utilities.errors.ParserError;

/**
 * A {@code FixedParser} parses a fixed string literal.
 *
 * @author Waffles
 * @since 27 Feb 2024
 * @version 1.1
 * 
 * 
 * @see Parser
 */
public class FixedParser implements Parser<String>
{
	private int len;
	private String src;
	
	/**
	 * Creates a new {@code FixedParser}.
	 * 
	 * @param s  a source string
	 */
	public FixedParser(String s)
	{
		src = s;
	}
	
	/**
	 * Returns the length of the {@code FixedParser}.
	 * 
	 * @return  a substring length
	 */
	public int Length()
	{
		return len;
	}
	
		
	@Override
	public boolean consume(Character s)
	{
		if(Length() < src.length())
		{
			char t = src.charAt(Length());
			if(s == t)
			{
				len++;
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String generate()
	{
		if(Length() < src.length())
		{
			throw new ParserError("Could not find string literal.");
		}
		
		return src;
	}
	
	@Override
	public void reset()
	{
		len = 0;
	}
}