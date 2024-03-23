package waffles.utils.dacs.utilities.parsers;

import waffles.utils.tools.patterns.semantics.Gateway;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code BasicParser} consumes characters iteratively into a substring.
 * A {@code Gateway} can be supplied at construction, which decides which
 * characters are allowed and which are not. In addition, this parser
 * always blocks character values which are equal to null.
 *
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 *
 * 
 * @param <O>  an output type
 * @see DemiParser
 * @see Gateway
 */
public abstract class BasicParser<O> implements DemiParser<String, O>, Gateway<Character>
{	
	private String subs;
	private Gateway<Character> gate;
	
	/**
	 * Creates a new {@code BasicParser}.
	 * 
	 * @param g  a character gate
	 * 
	 * 
	 * @see Gateway
	 */
	public BasicParser(Gateway<Character> g)
	{
		subs = "";
		gate = g;
	}
	
	/**
	 * Creates a new {@code BasicParser}.
	 * 
	 * @param set  a character set
	 */
	public BasicParser(char... set)
	{
		this(s -> Array.contents.has(set, s));
	}
		
	/**
	 * Creates a new {@code BasicParser}.
	 */
	public BasicParser()
	{
		subs = "";
	}
		
	/**
	 * Returns the length of the current substring.
	 * 
	 * @return  an input length
	 */
	public int Length()
	{
		return Input().length();
	}
	
	
	@Override
	public boolean consume(Character s)
	{
		if(allows(s))
		{
			subs += s;
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean allows(Character s)
	{
		if( gate != null)
			return gate.allows(s);
		return s != null;
	}
		
	@Override
	public String Input()
	{
		return subs;
	}
	
	@Override
	public void reset()
	{
		subs = "";
	}
}