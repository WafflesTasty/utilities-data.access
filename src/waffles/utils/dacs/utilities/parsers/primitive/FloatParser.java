package waffles.utils.dacs.utilities.parsers.primitive;

import waffles.utils.dacs.utilities.parsers.BasicParser;
import waffles.utils.tools.primitives.Array;
import waffles.utils.tools.primitives.Floats;

/**
 * A {@code FloatParser} parses an arbitrary floating-point value.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 * 
 * 
 * @see BasicParser
 */
public class FloatParser extends BasicParser<Float>
{
	private static final char[] SIGNS = new char[]{'-', '+'};
	
	private static final char PERIOD = '.';
	
	private static enum State
	{
		INITIAL,
		INTEGER,
		FRACTION,
		DONE;
	}
	
	
	private State state;
	
	/**
	 * Creates a new {@code FloatParser}.
	 * The default delimiter is '"'.
	 * The default escape is '\'.
	 */
	public FloatParser()
	{
		state = State.INITIAL;
	}
	
		
	@Override
	public boolean allows(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(Length() == 0)
			{
				if(Array.contents.has(SIGNS, s))
				{
					return true;
				}
			}
			
			if(Character.isDigit(s))
			{
				state = State.INTEGER;
				return true;
			}
			
			state = State.DONE;
			return false;
		}
		case INTEGER:
		{
			if(s == PERIOD)
			{
				state = State.FRACTION;
				return true;
			}
		}
		case FRACTION:
		{
			if(Character.isDigit(s))
			{
				return true;
			}
			
			state = State.DONE;
		}
		case DONE:
		default:
			return false;
		}
	}
	
	@Override
	public Float generate(String s)
	{
		return Floats.parse(s);
	}
	
	@Override
	public void reset()
	{
		super.reset();
		state = State.INITIAL;
	}
}