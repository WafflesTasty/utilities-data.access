package waffles.utils.dacs.utilities.parsers.objects;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.dacs.files.tokens.objects.TokenPair;

/**
 * A {@code PairParser} parses a key and a value into a {@code TokenPair}.
 *
 * @author Waffles
 * @since 19 Mar 2024
 * @version 1.1
 *
 *
 * @param <P>  a pair type
 * @see TokenPair
 * @see Parser
 */
public abstract class PairParser<P extends TokenPair<?, ?>> implements Parser<P>
{
	static enum State
	{
		BEFORE,
		DURING,
		AFTER,
		DONE;
	}
	

	private State state;
	private char separator;
	private boolean isSeparated;
	private Parser<?> curr, key, val;
	
	/**
	 * Creates a new {@code PairParser}.
	 * 
	 * @param s    a separator
	 */
	public PairParser(char s)
	{
		state = State.BEFORE;
		val = ValueParser();
		key = KeyParser();
		separator = s;
		curr = key;
	}
	
	
	/**
	 * Returns a {@code Parser} for a pair key.
	 * 
	 * @return  a key parser
	 * 
	 * 
	 * @see Parser
	 */
	public abstract Parser<?> KeyParser();
	
	/**
	 * Returns a {@code Parser} for a pair value.
	 * 
	 * @return  a value parser
	 * 
	 * 
	 * @see Parser
	 */
	public abstract Parser<?> ValueParser();
		
	
	/**
	 * Generates a pair from key and value objects.
	 * 
	 * @param k  a key object
	 * @param v  a value object
	 * @return  a key-value pair
	 */
	public abstract P generate(Object k, Object v);
		
	
	@Override
	public boolean consume(Character s)
	{
		switch(state)
		{
		case BEFORE:
		{
			if(Character.isWhitespace(s))
				return true;
			state = State.DURING;
		}
		case DURING:
		{
			if(curr.consume(s))
				return true;
			if(isSeparated)
			{
				state = State.DONE;
				return false;
			}
			
			state = State.AFTER;
		}
		case AFTER:
		{
			if(Character.isWhitespace(s))
				return true;
			if(s == separator)
			{
				state = State.BEFORE;
				isSeparated = true;
				curr = val;
				
				return true;
			}
			
			state = State.DONE;
			return false;
		}
		case DONE:
		default:
			return false;
		}
	}
	
	@Override
	public P generate()
	{
		return generate
		(
			key.generate(),
			val.generate()
		);
	}
	
	@Override
	public void reset()
	{
		isSeparated = false;
		state = State.BEFORE;
		val = ValueParser();
		key = KeyParser();
		curr = key;
	}
}