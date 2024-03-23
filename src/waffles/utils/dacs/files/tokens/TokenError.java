package waffles.utils.dacs.files.tokens;

/**
 * A {@code TokenError} is thrown if a {@code TokenReader} fails to read a file correctly.
 *
 * @author Waffles
 * @since Sep 17, 2019
 * @version 1.0
 * 
 * 
 * @see RuntimeException
 */
public class TokenError extends RuntimeException
{
	private static final long serialVersionUID = -6720832754440656496L;

	/**
	 * Creates a new {@code TokenError}.
	 * 
	 * @param sub  a substring
	 */
	public TokenError(String sub)
	{
		super("Could not find any token for " + sub + ".");
	}
}