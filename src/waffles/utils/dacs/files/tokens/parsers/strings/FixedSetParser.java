package waffles.utils.dacs.files.tokens.parsers.strings;

import waffles.utils.dacs.files.tokens.parsers.ChoiceParser;
import waffles.utils.lang.tokens.parsers.strings.FixedParser;
import waffles.utils.tools.collections.Iterables;
import waffles.utils.tools.collections.iterators.ConvertIterator;

/**
 * A {@code FixedSetParser} parses one of a set of fixed string literals.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 *
 *
 * @param <O>  an output type
 * @see ChoiceParser
 */
public class FixedSetParser<O> extends ChoiceParser<String, O>
{
	private static class Iterator extends ConvertIterator<String, FixedParser.Hints>
	{
		private Hints hints;
		
		/**
		 * Creates a new {@code FixedSetParser.Iterator}.
		 * 
		 * @param h  parser hints
		 * 
		 * 
		 * @see Hints
		 */
		public Iterator(Hints h)
		{
			super(Iterables.of(h.Set()));
			hints = h;
		}

		
		@Override
		public FixedParser.Hints compute(String s)
		{
			return new FixedParser.Hints()
			{
				@Override
				public boolean ignoreCase()
				{
					return hints.ignoreCase();
				}
				
				@Override
				public String Label()
				{
					return s;
				}
			};
		}
	}
	
	/**
	 * The {@code Hints} interface defines settings for a {@code FixedSetParser}.
	 *
	 * @author Waffles
	 * @since 09 Aug 2025
	 * @version 1.1
	 */
	@FunctionalInterface
	public static interface Hints extends Iterable<FixedParser.Hints>
	{
		/**
		 * Returns the strings of the {@code Hints}.
		 * 
		 * @return  a string set
		 */
		public abstract String[] Set();
		
		/**
		 * Indicates whether to ignore case.
		 * 
		 * @return  {@code true} to ignore case
		 */
		public default boolean ignoreCase()
		{
			return false;
		}
		
		
		@Override
		public default Iterator iterator()
		{
			return new Iterator(this);
		}
	}
	
	
	private Hints hints;

	/**
	 * Creates a new {@code FixedSetParser}.
	 * 
	 * @param set  a fixed string set
	 */
	public FixedSetParser(String... set)
	{
		this(() -> set);
	}

	/**
	 * Creates a new {@code FixedSetParser}.
	 * 
	 * @param h  parser hints
	 * 
	 * 
	 * @see Hints
	 */
	public FixedSetParser(Hints h)
	{
		hints = h;
		for(FixedParser.Hints fph : Hints())
		{
			add(new FixedParser(fph));
		}
	}
	
	/**
	 * Returns {@code FixedSetParser} hints.
	 * 
	 * @return  parser hints
	 * 
	 * 
	 * @see Hints
	 */
	public Hints Hints()
	{
		return hints;
	}
}