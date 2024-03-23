package waffles.utils.dacs.utilities.parsers.choice;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.dacs.utilities.parsers.DemiParser;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.sets.keymaps.delegate.JHashMap;

/**
 * A {@code DemiChoiceParser} handles a list of parsers simultaneously.
 * A token is generated from the parser that consumed the longest string.
 * In contrast with the regular {@code ChoiceParser}, this class has been
 * implemented as a {@code DemiParser} to pass intermediary results.
 *
 * @author Waffles
 * @since 13 Dec 2023
 * @version 1.1
 * 
 * 
 * @param <I>  an input type
 * @param <O>  an output type
 * @see DemiParser
 * @see List
 */
public abstract class DemiChoiceParser<I, O> extends List<Parser<? extends I>> implements DemiParser<I, O>
{
	private Parser<? extends I> curr;
	private JHashMap<Parser<? extends I>, Boolean> map;

	/**
	 * Creates a new {@code DemiChoiceParser}.
	 */
	public DemiChoiceParser()
	{
		map = new JHashMap<>();
	}
	
	
	@Override
	public boolean consume(Character s)
	{
		boolean isConsumed = false;
		for(int i = Count()-1; i >= 0; i--)
		{
			Parser<? extends I> p = get(i);
			
			if(map.get(p))
			{
				if(!p.consume(s))
					map.put(p, false);
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
	public void remove(Parser<? extends I> p)
	{
		super.remove(p);
		map.remove(p);
	}

	@Override
	public void add(Parser<? extends I> p)
	{
		super.add(p);
		map.put(p, true);
	}
		
	
	@Override
	public void clear()
	{
		map.clear();
		reset();
	}

	@Override
	public void reset()
	{
		curr = null;
		for(Parser<? extends I> p : map.Keys())
		{
			map.put(p, true);
			p.reset();
		}
	}

	@Override
	public int Count()
	{
		return map.Count();
	}

	@Override
	public I Input()
	{
		return curr.generate();
	}
}