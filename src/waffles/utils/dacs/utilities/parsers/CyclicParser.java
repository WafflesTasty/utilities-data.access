package waffles.utils.dacs.utilities.parsers;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.dacs.utilities.parsers.primitive.NullParser;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.tools.patterns.semantics.Producable;
import waffles.utils.tools.primitives.Integers;

/**
 * 
 * A {@code CyclicParser} consumes characters into a list of objects.
 * The objects are separated by a delimiter, with whitespace between
 * objects and delimiters being automatically ignored.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an output type
 * @see Producable
 * @see Parser
 * @see List
 */
public abstract class CyclicParser<O> extends List<Parser<O>> implements Parser<List<O>>, Producable<Parser<O>>
{
	static enum State
	{
		INITIAL,
		OBJECT,
		DELIMIT,
		DONE;
	}
	
	
	private char delim;
	private int curr, max;
	private Parser<O> pars;
	private State state;
	
	/**
	 * Creates a new {@code CyclicParser}.
	 * 
	 * @param d    an object delimiter
	 * @param oMax  an object maximum
	 */
	public CyclicParser(char d, int oMax)
	{
		state = State.INITIAL;
		
		max = oMax;
		delim = d;
	}
	
	/**
	 * Creates a new {@code CyclicParser}.
	 * 
	 * @param d  an object delimiter
	 */
	public CyclicParser(char d)
	{
		this(d, Integers.MAX_VALUE);
	}
	
	
	
		
	@Override
	public boolean consume(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(curr == max)
			{
				state = State.DONE;
				return false;
			}
			
			if(Character.isWhitespace(s))
				return true;
			if(s == delim)
			{
				add(new NullParser<>());
				curr++; return true;
			}
			
			state = State.OBJECT;
			pars = produce();
		}
		case OBJECT:
		{
			if(pars.consume(s))
				return true;
		
			curr++;
			add(pars);
			pars = null;
	
			state = State.DELIMIT;
			return consume(s);
		}
		case DELIMIT:
		{
			if(curr == max)
			{
				state = State.DONE;
				return false;
			}
			
			if(Character.isWhitespace(s))
				return true;
			if(s == delim)
			{
				state = State.INITIAL;
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
		curr = 0;
		state = State.INITIAL;
		pars = null;
		clear();
	}
}