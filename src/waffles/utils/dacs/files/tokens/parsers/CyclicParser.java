package waffles.utils.dacs.files.tokens.parsers;

import waffles.utils.lang.Characters;
import waffles.utils.lang.tokens.parsers.Parsable;
import waffles.utils.sets.indexed.delegate.List;
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
 * @param <O>  an object type
 * @see Parsable
 * @see List
 */
public class CyclicParser<O> implements Parsable<List<O>>
{
	static enum State
	{
		INITIAL,
		OBJECT,
		DELIMIT,
		DONE;
	}

	/**
	 * Defines a default separator for the {@code CyclicParser}.
	 */
	public static final char SEPARATOR = ';';
	
	/**
	 * The {@code Hints} interface defines {@code CyclicParser} settings.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 *
	 *
	 * @param <O>  an object type
	 */
	public static interface Hints<O>
	{
		/**
		 * Returns the parsable of the {@code Hints}.
		 * 
		 * @return  a parsable object
		 * 
		 * 
		 * @see Parsable
		 */
		public abstract Parsable<O> Parser();
		
		
		/**
		 * Returns the separator of the {@code Hints}.
		 * 
		 * @return  a delimiter
		 */
		public default char Separator()
		{
			return SEPARATOR;
		}
		
		/**
		 * Returns the maximum of the {@code Hints}.
		 * 
		 * @return  an object maximum
		 */
		public default int Maximum()
		{
			return Integers.MAX_VALUE;
		}
	}


	private int curr;
	private List<O> data;
	private Parsable<O> prs;
	private Hints<O> hints;
	private State state;
	
	/**
	 * Creates a new {@code CyclicParser}.
	 * 
	 * @param h  parser hints
	 * 
	 * 
	 * @see Hints
	 */
	public CyclicParser(Hints<O> h)
	{
		state = State.INITIAL;
		data = new List<>();
		hints = h;
	}
	
	/**
	 * Returns {@code CyclicParser} hints.
	 * 
	 * @return  parser hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints<O> Hints()
	{
		return hints;
	}
	
		
	@Override
	public boolean consume(Character s)
	{
		switch(state)
		{
		case INITIAL:
		{
			if(curr == Hints().Maximum())
			{
				state = State.DONE;
				return false;
			}
			
			if(Characters.isWhiteSpace(s))
				return true;
			if(s == Hints().Separator())
			{
				curr++; data.add(null);
				return true;
			}
			
			prs = Hints().Parser();
			state = State.OBJECT;
		}
		case OBJECT:
		{
			if(prs.consume(s))
				return true;
		
			state = State.DELIMIT;
			O obj = prs.generate();
			curr++; data.add(obj);
			return consume(s);
		}
		case DELIMIT:
		{
			if(curr == Hints().Maximum())
			{
				state = State.DONE;
				return false;
			}
			
			if(Characters.isWhiteSpace(s))
				return true;
			if(s == Hints().Separator())
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
		return data;
	}
	
	@Override
	public void reset()
	{
		prs = Hints().Parser();
		state = State.INITIAL;
		data.clear();
		curr = 0;
	}
}