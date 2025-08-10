package waffles.utils.dacs.files.tokens.parsers.cyclic;

import waffles.utils.dacs.files.tokens.parsers.CyclicParser;
import waffles.utils.lang.Characters;

/**
 * 
 * A {@code ListParser} consumes characters into a list of objects.
 * The objects are separated by a separator, with whitespace between
 * objects and separators being automatically ignored. In addition
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
public class ListParser<O> extends CyclicParser<O>
{	
	/**
	 * The {@code Hints} interface defines {@code ListParser} settings.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an output type
	 * @see CyclicParser
	 */
	public static interface Hints<O> extends CyclicParser.Hints<O>
	{
		/**
		 * Returns the lower bound of the {@code Hints}.
		 * 
		 * @return  a lower bound
		 */
		public abstract char LowerBound();
		
		/**
		 * Returns the upper bound of the {@code Hints}.
		 * 
		 * @return  an upper bound
		 */
		public abstract char UpperBound();
	}

	private static enum State
	{
		INITIAL,
		RUNNING,
		DONE;
	}
	

	private State state;
	
	/**
	 * Creates a new {@code ListParser}.
	 * 
	 * @param h  parser hints
	 * 
	 * 
	 * @see Hints
	 */
	public ListParser(Hints<O> h)
	{
		super(h);
		state = State.INITIAL;
	}


	@Override
	public Hints<O> Hints()
	{
		return (Hints<O>) super.Hints();
	}
	
	@Override
	public boolean consume(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(Characters.isWhiteSpace(s))
				return true;
			if(s == Hints().LowerBound())
			{
				state = State.RUNNING;
				return true;
			}
			
			state = State.DONE;
			return false;
		}
		case RUNNING:
		{
			if(s == Hints().UpperBound())
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
	public void reset()
	{
		state = State.INITIAL;
		super.reset();
	}
}