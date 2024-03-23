package waffles.utils.dacs.utilities.parsers.choice;

import waffles.utils.dacs.files.tokens.Token.Parser;
import waffles.utils.sets.indexed.delegate.List;
import waffles.utils.sets.keymaps.delegate.JHashMap;

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
 * @param <O>  an output type
 * @see Parser
 * @see List
 */
public class ChoiceParser<O> extends List<Parser<? extends O>> implements Parser<O>
{
	private Parser<? extends O> curr;
	private JHashMap<Parser<? extends O>, Boolean> map;

	/**
	 * Creates a new {@code ChoiceParser}.
	 */
	public ChoiceParser()
	{
		map = new JHashMap<>();
	}

		
	@Override
	public boolean consume(Character s)
	{
		boolean isConsumed = false;
		for(int i = Count()-1; i >= 0; i--)
		{
			Parser<? extends O> p = get(i);
			
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
	public void remove(Parser<? extends O> p)
	{
		super.remove(p);
		map.remove(p);
	}

	@Override
	public void add(Parser<? extends O> p)
	{
		super.add(p);
		map.put(p, true);
	}
		
	
	@Override
	public O generate()
	{
		return curr.generate();
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
		for(Parser<? extends O> p : map.Keys())
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
}