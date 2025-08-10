package waffles.utils.dacs.files.tokens.parsers.strings;

import waffles.utils.lang.Characters;
import waffles.utils.lang.tokens.parsers.basic.BasicParser;
import waffles.utils.tools.primitives.Array;

/**
 * A {@code DelimitParser} parses an arbitrary string enclosed by delimiters.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 * 
 * 
 * @see BasicParser
 */
public class DelimitParser extends BasicParser<String>
{
	/**
	 * Defines a default delimiter.
	 */
	public static final char DELIM = '"';
	/**
	 * Defines a default escape.
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
	private char esc, low, upp;
	
	/**
	 * Creates a new {@code DelimitParser}.
	 * The default delimiter is '"'.
	 * The default escape is '\'.
	 */
	public DelimitParser()
	{
		this(DELIM);
	}
	
	/**
	 * Creates a new {@code DelimitParser}.
	 * The default escape is '\'.
	 * 
	 * @param d  a delimiter
	 */
	public DelimitParser(char d)
	{
		this(d, d);
	}
	
	/**
	 * Creates a new {@code DelimitParser}.
	 * 
	 * @param l   a lower delimiter
	 * @param u  an upper delimiter
	 */
	public DelimitParser(char l, char u)
	{
		state = State.INITIAL;
		low = l; upp = u;
		esc = ESCAPER;
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
			if(Characters.isWhiteSpace(s))
				return true;
			
			if(s == low)
			{
				state = State.RUNNING;
				return true;
			}
			
			state = State.FAILED;
			return false;
		}
		case RUNNING:
		{
			if(s == esc)
				state = State.ESCAPE;
			if(s == upp)
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
	public String compute(String s)
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