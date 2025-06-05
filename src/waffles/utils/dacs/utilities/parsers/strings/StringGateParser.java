package waffles.utils.dacs.utilities.parsers.strings;

import waffles.utils.dacs.utilities.parsers.DemiParser;

/**
 * A {@code StringGateParser} parses a string gated by a delimiter.
 * A string can be preceded by whitespace but must otherwise
 * start with the delimiter provided to the parser,
 * i.e. when the delimiter is the hashtag # then
 * the following line is a valid string.
 * <p>
 * # This is a comment.
 *
 * @author Waffles
 * @since 22 Mar 2024
 * @version 1.1
 *
 * 
 * @param <O>  an output type
 * @see StringGateParser
 * @see DemiParser
 */
public abstract class StringGateParser<O> implements DemiParser<String, O>
{
	static enum State
	{
		INITIAL,
		FINAL,
		FAIL;
	}
	
	
	private char delim;
	private State state;
	private AnyParser any;

	/**
	 * Creates a new {@code StringGateParser}.
	 * 
	 * @param d  a delimiter
	 */
	public StringGateParser(char d)
	{
		any = new AnyParser();
		state = State.INITIAL;
		delim = d;
	}

	
	@Override
	public String Input()
	{
		return any.generate();
	}
	
	@Override
	public boolean consume(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(Character.isWhitespace(s))
				return true;
			if(s == delim)
			{
				state = State.FINAL;
				return true;
			}
			
			state = State.FAIL;
			return false;
		}
		case FINAL:
			return any.consume(s);
		case FAIL:
		default:
			return false;
		}
	}
	
	@Override
	public void reset()
	{
		state = State.INITIAL;
		any = new AnyParser();
	}
}