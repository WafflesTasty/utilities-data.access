package waffles.utils.dacs.utilities.parsers.objects;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.dacs.utilities.parsers.CyclicParser;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.tools.primitives.Integers;

/**
 * 
 * A {@code ListParser} consumes characters into a list of objects.
 * The objects are separated by a delimiter, with whitespace between
 * objects and delimiters being automatically ignored. In addition
 * to this, the list is bounded by an upper and lower character.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an output type
 * @see CyclicParser
 */
public abstract class ListParser<O> extends CyclicParser<O>
{
	static enum State
	{
		INITIAL,
		RUNNING,
		DONE;
	}
	
	
	private State state;
	private char upper, lower;
	
	/**
	 * Creates a new {@code ListParser}.
	 * 
	 * @param u    an upper symbol
	 * @param d    a list delimiter
	 * @param l    a lower symbol
	 * @param oMax a list maximum
	 */
	public ListParser(char u, char d, char l, int oMax)
	{
		super(d, oMax);
		state = State.INITIAL;
		upper = u; lower = l;
	}
	
	/**
	 * Creates a new {@code ListParser}.
	 * 
	 * @param u  an upper symbol
	 * @param d  a list delimiter
	 * @param l  a lower symbol
	 */
	public ListParser(char u, char d, char l)
	{
		this(u, d, l, Integers.MAX_VALUE);
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
			if(s == upper)
			{
				state = State.RUNNING;
				return true;
			}
			
			state = State.DONE;
			return false;
		}
		case RUNNING:
		{
			if(s == lower)
			{
				state = State.DONE;
				return true;
			}
			
			return super.consume(s);			
		}
		case DONE:
		default:
			return false;
		}
	}

	@Override
	public List<O> generate()
	{
		List<O> list = new List<>();
		for(Parser<O> p : this)
		{
			list.add(p.generate());
		}
		
		return list;
	}

	@Override
	public void reset()
	{
		state = State.INITIAL;
	}
}