package waffles.utils.dacs.files.tokens;

import waffles.utils.lang.Formattable;
import waffles.utils.tools.patterns.semantics.Consumable;

/**
 * A {@code Token} is an object uniquely represented by a {@code String}.
 *
 * @author Waffles
 * @since 05 Dec 2023
 * @version 1.1
 */
@FunctionalInterface
public interface Token extends Formattable
{
	/**
	 * A {@code Token.Parser} consume characters sequentially to create tokens.
	 *
	 * @author Waffles
	 * @since 08 Dec 2023
	 * @version 1.1
	 * 
	 * 
	 * @param <O>  an output type
	 * @see Consumable
	 */
	public static interface Parser<O> extends Consumable<Character>
	{		
		/**
		 * Resets the contents of the {@code Parser}.
		 */
		public abstract void reset();
		
		/**
		 * Generates an object from the {@code Parser}.
		 * All currently consumed symbols should be used.
		 * 
		 * 
		 * @return  a parsed token
		 */
		public abstract O generate();
	}
	
	/**
	 * A {@code Token.Parsable} generates an object from input.
	 * Both types are left generic to support the
	 * {@code Parser} hierarchy.
	 *
	 * @author Waffles
	 * @since 18 Mar 2024
	 * @version 1.1
	 *
	 *
	 * @param <I>  an input type
	 * @param <O>  an output type
	 */
	@FunctionalInterface
	public static interface Parsable<I, O>
	{
		/**
		 * Generates an object from input.
		 * 
		 * @param obj  an input object
		 * @return  an output object
		 */
		public abstract O generate(I obj);
	}
}