package waffles.utils.dacs.files.tokens.parsers.primitive;

import waffles.utils.lang.tokens.parsers.basic.BasicParser;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Integers;

/**
 * An {@code IntegerParser} parses a signed integer from a string.
 *
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 *
 * 
 * @see BasicParser
 */
public class IntegerParser extends BasicParser<Integer>
{
	private static final char[] SIGNS = new char[]{'-', '+'};

	private static enum State
	{
		/**
		 * An initial state.
		 */
		INITIAL,
		/**
		 * An integer state.
		 */
		INTEGER,
		/**
		 * A final state.
		 */
		FINAL;
	}
	
	
	private State state;
	
	/**
	 * Creates a new {@code IntegerParser}.
	 */
	public IntegerParser()
	{
		state = State.INITIAL;
	}
	
	
	@Override
	public Integer compute(String s)
	{
		return Integers.parse(s);
	}

	@Override
	public boolean allows(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(Array.contents.has(SIGNS, s))
			{
				state = State.INTEGER;
				return true;
			}
			
			state = State.INTEGER;
		}
		case INTEGER:
		{
			if(Character.isDigit(s))
			{
				return true;
			}
			
			state = State.FINAL;
		}
		case FINAL:
		default:
			return false;
		}
	}
	
	@Override
	public void reset()
	{
		super.reset();
		state = State.INITIAL;
	}
}