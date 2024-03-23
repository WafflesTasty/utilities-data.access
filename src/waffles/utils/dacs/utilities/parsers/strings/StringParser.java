package waffles.utils.dacs.utilities.parsers.strings;

import waffles.utils.dacs.utilities.parsers.BasicParser;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code StringParser} parses an arbitrary string enclosed by double quotes '"'.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 * 
 * 
 * @see BasicParser
 */
public class StringParser extends BasicParser<String>
{
	/**
	 * Defines the default delimiter used for parsing {@code Strings}.
	 */
	public static final char DELIM = '"';
	/**
	 * Defines the default escape used for parsing {@code Strings}.
	 */
	public static final char ESCAPER = '\\';

	
	private char[] ESCAPEES = new char[]{'"', '\\', '/', 'b', 'f', 'n', 'r', 't'};
	
	private static enum State
	{
		INITIAL,
		RUNNING,
		ESCAPE,
		FAILED,
		DONE;
	}
	
	
	private State state;
	private char escape;
	private char upper, lower;
	
	/**
	 * Creates a new {@code StringParser}.
	 * The default delimiter is '"'.
	 * The default escape is '\'.
	 */
	public StringParser()
	{
		this(DELIM);
	}
	
	/**
	 * Creates a new {@code StringParser}.
	 * The default escape is '\'.
	 * 
	 * @param d  a delimiter
	 */
	public StringParser(char d)
	{
		this(d, d);
	}
	
	/**
	 * Creates a new {@code StringParser}.
	 * 
	 * @param u  an upper delimiter
	 * @param l   a lower delimiter
	 */
	public StringParser(char u, char l)
	{
		state = State.INITIAL;
		escape = ESCAPER;
		upper = u;
		lower = l;
	}	
	
	
	protected boolean isEscapee(char s)
	{
		return Array.contents.has(ESCAPEES, s);
	}
		
	@Override
	public boolean allows(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(s == upper)
			{
				state = State.RUNNING;
				return true;
			}
			
			state = State.FAILED;
			return false;
		}
		case RUNNING:
		{
			if(s == escape)
				state = State.ESCAPE;
			if(s == lower)
				state = State.DONE;
			return true;
		}
		case ESCAPE:
		{
			if(isEscapee(s))
			{
				state = State.RUNNING;
				return true;
			}
			
			state = State.FAILED;
		}
		case FAILED:
		case DONE:
		default:
			return false;
		}
	}
	
	@Override
	public String generate(String s)
	{
		if(state == State.DONE)
		{
			return s.substring(1, s.length() - 1);
		}
		
		return null;
	}
	
	@Override
	public void reset()
	{
		super.reset();
		state = State.INITIAL;
	}
}