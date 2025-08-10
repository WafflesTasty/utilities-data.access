package waffles.utils.dacs.files.tokens.parsers;

import waffles.utils.lang.tokens.parsers.Parsable;
import waffles.utils.sets.MutableSet;
import waffles.utils.sets.keymaps.IndexMap;
import waffles.utils.sets.utilities.Iterables;
import waffles.utils.tools.patterns.Computable;

/**
 * A {@code ChoiceParser} handles a list of parsers simultaneously.
 * A token is generated from the parser that consumed the longest string.
 * In case of a tie, the first parser in the list will be chosen.
 * 
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 * 
 * 
 * @param <I>  an input type
 * @param <O>  an output type
 * @see MutableSet
 * @see IndexMap
 * @see Parsable
 */
public class ChoiceParser<I, O> extends IndexMap<Parsable<?>, Boolean> implements Computable<I, O>, Parsable<O>
{
	private Parsable<?> curr;
	
	/**
	 * Adds a parser to the {@code ChoiceParser}.
	 * 
	 * @param p  a token parser
	 * 
	 * 
	 * @see Parsable
	 */
	public void add(Parsable<?> p)
	{
		put(p, true);
	}
	
	/**
	 * Iterates over the parsers of the {@code ChoiceParser}.
	 * Iteration happens in reverse, to prioritize the
	 * parser that is added last in the map.
	 * 
	 * @return  a parser iterable
	 * 
	 * 
	 * @see Iterable
	 * @see Parsable
	 */
	public Iterable<Parsable<?>> Parsers()
	{
		return Iterables.reverseOf(this);
	}

	
	@Override
	public O generate()
	{
		if(curr != null)
		{
			I ipt = (I) curr.generate();
			return compute(ipt);
		}
		
		return null;
	}
	
	@Override
	public O compute(I input)
	{
		return (O) input;
	}
		
	@Override
	public boolean consume(Character s)
	{
		boolean isConsumed = false;
		for(Parsable<?> p : Parsers())
		{
			if(get(p))
			{
				if(!p.consume(s))
					put(p, false);
				else
				{
					isConsumed = true;
					curr = p;
				}
			}
		}
		
		return isConsumed;
	}
	
	
	@Override
	public void clear()
	{
		super.clear();
		curr = null;
	}

	@Override
	public void reset()
	{
		curr = null;
		for(Parsable<?> p : Parsers())
		{
			put(p, true);
			p.reset();
		}
	}
}