package waffles.utils.dacs.utilities.parsers;

import waffles.utils.dacs.files.tokens.Token;
import waffles.utils.dacs.files.tokens.Token.Parsable;
import waffles.utils.dacs.files.tokens.Token.Parser;

/**
 * A {@code DemiParser} parses an input type to an output type.
 *
 * @author Waffles
 * @since 18 Mar 2024
 * @version 1.1
 *
 *
 * @param <I>  an input type
 * @param <O>  an output type
 * @see Parsable
 * @see Parser
 */
public interface DemiParser<I, O> extends Token.Parser<O>, Parsable<I, O>
{
	/**
	 * Returns the input of the {@code Parser}.
	 * 
	 * @return  a parser input
	 */
	public abstract I Input();
	
	
	@Override
	public default O generate()
	{
		return generate(Input());
	}
}