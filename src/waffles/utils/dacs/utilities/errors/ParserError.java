package waffles.utils.dacs.utilities.errors;

/**
 * A {@code ParserError} is thrown whenever a parser fails to generate a token.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see RuntimeException
 */
public class ParserError extends RuntimeException
{
	private static final long serialVersionUID = -3602621371383175152L;


	/**
	 * Creates a new {@code ParserError}.
	 * 
	 * @param desc  a description text
	 */
	public ParserError(String desc)
	{
		super(desc);
	}
}